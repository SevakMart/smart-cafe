package am.smartcafe.presentation.controller;

import am.smartcafe.dataaccess.model.User;
import am.smartcafe.dataaccess.repository.UserRepository;
import am.smartcafe.service.impl.MailServiceImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final MailServiceImpl mailService;
    private final UserRepository userRepository;

    public LoginController(MailServiceImpl mailService, UserRepository userRepository) {
        this.mailService = mailService;
        this.userRepository = userRepository;
    }

    @GetMapping("/loginPage")
    public String login() {

        return "login";
    }

    @GetMapping("/loginPage/forgotPassword")
    public String forgotPassword() {
        return "forgotPassword";
    }

    @PostMapping("/loginPage/forgotPassword")
    public String forgotPasswordPost(@RequestParam("email") String email) throws UsernameNotFoundException {

        String link = "http://localhost:8080/loginPage/forgotPassword/recoverPassword";
        String subject = "Your verification link and password";

        User byEmail = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("xczcxzc"));

        mailService.send(email, subject, mailService.generatePassword() + "\n" + link);

        return "redirect:/loginPage";
    }

    @GetMapping("/loginPage/forgotPassword/recoverPassword")
    public String recoverPassword() {
        return "recoverPassword";
    }

}
