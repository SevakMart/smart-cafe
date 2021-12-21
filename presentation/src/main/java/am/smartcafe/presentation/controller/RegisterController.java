package am.smartcafe.presentation.controller;

import am.smartcafe.dto.req.UserRegisterRequest;
import am.smartcafe.dto.resp.UserResponse;
import am.smartcafe.data_access.model.User;
import am.smartcafe.service.UserService;
import am.smartcafe.util.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class RegisterController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String userPage(ModelMap modelMap) {
        modelMap.addAttribute("");
        return "register";
    }


    @PostMapping("/register")
    public String createNewUser(@Valid @ModelAttribute UserRegisterRequest userRegisterRequest,ModelMap modelMap, BindingResult bindingResult){
        Optional<User> userExists = userService.findByEmail(userRegisterRequest.getEmail());
        if (userExists.isPresent()) {
            bindingResult.rejectValue("email", "error.user",
                    "User with this email already exists. Login or reset your password.");
        }
        if (bindingResult.hasErrors()){
            return "redirect:/register";
        }
        UserResponse userResponse = userService.saveUser(UserMapper.dtoToUser(userRegisterRequest));
        modelMap.addAttribute("user",userResponse);
        return "redirect:/?msg= User was added";
    }
}
