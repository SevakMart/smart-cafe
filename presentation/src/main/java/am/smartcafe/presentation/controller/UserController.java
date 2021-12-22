package am.smartcafe.presentation.controller;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

  private final String message;
  private final UserService userService;

  public UserController(@Value("${text}") String message, UserService userService) {
    this.message = message;
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
    return "redirect:/user/register?msg=" + message + user.getEmail();
  }
}
