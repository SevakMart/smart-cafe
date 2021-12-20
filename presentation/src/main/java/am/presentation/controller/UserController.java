package am.presentation.controller;

import am.smartcafe.impl.UserService;
import am.smartcafe.model.User;
import am.smartcafe.model.PasswordChangeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/changePassword")
    public String changePassword(@RequestParam("userId") long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(PasswordChangeRequest passwordChangeRequest, Model model, Locale locale) {
        User user = userService.getById(passwordChangeRequest.getUserId());
        if (!passwordEncoder.matches(passwordChangeRequest.getPassword(),user.getPassword()) ||
                !passwordChangeRequest.getRepeatNewPassword().equals(passwordChangeRequest.getNewPassword())
                || !UserService.REGEX.matches(passwordChangeRequest.getNewPassword())) {
            model.addAttribute("errorMessage", messageSource.getMessage("change.password.message",null,locale));
            return "/changePassword";
        }
        userService.changePassword(passwordChangeRequest);
        return "redirect:/login";
    }
}
