package am.smartcafe.presentation.controller;

import am.smartcafe.data_access.model.User;
import am.smartcafe.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;


@RequestMapping( "/user")
@Controller
public class UserController {
    @Value("${text}")
    private String msg;

   @Autowired
    private UserServiceImpl userService;


    @PostMapping( "/register")
    public String saveUser(@ModelAttribute  User user) {
        Optional<User> userByEmail = userService.findByEmail(user.getEmail());
        if (userByEmail.isPresent()) {
            return "redirect:/user/register?msg=Email already used";
        }
        user.setActive(false);
        user.setPassword(passwordEncoder().encode(UUID.randomUUID().toString()));
        userService.saveUser(user);
        return "redirect:/?msg="+msg+ user.getEmail();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
