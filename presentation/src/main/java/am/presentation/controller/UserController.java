package am.presentation.controller;

import am.smartcafe.impl.UserService;
import am.smartcafe.model.User;
import am.smartcafe.model.UserChangePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/changePassword")
    public String changePassword(@RequestParam("userId") long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(UserChangePassword userChangePassword, Model model) {
        String result = userService.changePassword(userChangePassword);
        if (result == null) {
            return "redirect:/login";
        }
        model.addAttribute("errorMessage", result);
        return "/changePassword";
    }
}
