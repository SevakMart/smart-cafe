package am.smartcafe.presentation.controller;

import am.smartcafe.data_access.model.User;
import am.smartcafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping( "/user")
public class UserController {
    @Value("${text}")
    private String message;

   @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @PostMapping( "/register")
    public String saveUser(@ModelAttribute  User user) {
        Optional<User> userByEmail = userService.findByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            return "redirect:/user/register?msg=Email already used";
        }
        user.setActive(false);
        user.setPassword(passwordEncoder().encode(UUID.randomUUID().toString()));
        userService.saveUser(user);
        return "redirect:/user/register?msg="+message+ user.getEmail();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
