package am.smartcafe.presentation.user.controller;

import java.util.Locale;
import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import am.smartcafe.data_access.user.model.User;
import am.smartcafe.service.user.UserService;
import am.smartcafe.service.user.dto.req.PwdChangeRequest;

@Controller
@RequestMapping("/user")
public class UserController {

  private final MessageSource messageSource;
  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  public UserController(
      MessageSource messageSource, UserService userService, PasswordEncoder passwordEncoder) {
    this.messageSource = messageSource;
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @GetMapping("/register")
  public String registerPage() {
    return "register";
  }

  @PostMapping("/register")
  public String saveUser(@ModelAttribute User user) {
    Optional<User> userByEmail = userService.findByEmail(user.getEmail());
    if (userByEmail.isPresent()) {
      return "redirect:/user/register?msg=Email already used";
    }
    user.setActive(false);
    userService.save(user);
    return "redirect:/user/register?msg="
        + messageSource.getMessage("user.register.success.message", new Object[0], Locale.ENGLISH)
        + user.getEmail();
  }

  @GetMapping("/changePassword")
  public String changePassword(@RequestParam("userId") long id, Model model) {
    User user = userService.getById(id);
    model.addAttribute("user", user);
    return "changePassword";
  }

  @PostMapping("/changePassword")
  public String changePassword(
          PwdChangeRequest pwdChangeRequest, Model model, Locale locale) {
    User user = userService.getById(pwdChangeRequest.getUserId());
    if (!passwordEncoder.matches(pwdChangeRequest.getPassword(), user.getPassword())
        || !pwdChangeRequest
            .getRepeatNewPassword()
            .equals(pwdChangeRequest.getNewPassword())
        || !UserService.REGEX.matches(pwdChangeRequest.getNewPassword())) {
      model.addAttribute(
          "errorMessage", messageSource.getMessage("change.password.message", null, locale));
      return "/changePassword";
    }
    userService.changePassword(pwdChangeRequest);
    return "redirect:/login";
  }
}
