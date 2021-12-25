package am.smartcafe.presentation.user.controller;

import am.smartcafe.data_access.user.model.EmailExpiration;
import am.smartcafe.data_access.user.model.User;
import am.smartcafe.data_access.user.repository.UserRepository;
import am.smartcafe.service.email.MailServiceImpl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final MailServiceImpl mailService;
    private final UserRepository userRepository;
EmailExpiration emailExpiration = new EmailExpiration();
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
        String link;
if (emailExpiration.isPasswordExpired()){
        link = "http://localhost:8080/loginPage/forgotPassword/expirationPassword";
        return "expirationPassword";
        }else
            link = "http://localhost:8080/loginPage/forgotPassword/recoverPassword";

        String subject = "Your verification link and password";

        User byEmail = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("xczcxzc"));

        mailService.send(email, subject, mailService.generatePassword() + "\n" + link);

        return "redirect:/loginPage";
    }

    @GetMapping("/loginPage/forgotPassword/recoverPassword")
    public String recoverPassword() {
        return "recoverPassword";
    }

    @GetMapping("/loginPage/forgotPassword/expirationPassword")
    public String expirationPassword() {
        return "expirationPassword";
    }
}
