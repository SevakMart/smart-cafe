package am.presentation.controller;

import java.util.Locale;
import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import am.smartcafe.data_access.model.User;
import am.smartcafe.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

  private final MessageSource messageSource;
  private final UserService userService;

  public UserController(MessageSource messageSource, UserService userService) {
    this.messageSource = messageSource;
    this.userService = userService;
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
}
