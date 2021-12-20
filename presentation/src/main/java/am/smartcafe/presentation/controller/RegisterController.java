package am.smartcafe.presentation.controller;

import am.smartcafe.data_access.dto.req.UserRegisterRequest;
import am.smartcafe.data_access.model.User;
import am.smartcafe.presentation.exception.ModelAlreadyExistException;
import am.smartcafe.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("/register")
    public ResponseEntity <?> createNewUser(@RequestBody UserRegisterRequest userRegisterRequest, BindingResult bindingResult) throws ModelAlreadyExistException, ModelAlreadyExistException {
        ModelAndView modelAndView = new ModelAndView();
        Optional<User> userExists = userService.findUserByEmail(userRegisterRequest.getEmail());
        if (userExists.isPresent()) {
            bindingResult.rejectValue("email", "error.user",
                    "User with this email already exists. Login or reset your password.");
        }
        if (!bindingResult.hasErrors()) {
            userService.register(userRegisterRequest);
            modelAndView.addObject("successMessage",
                    "User has been registered successfully");
            modelAndView.addObject("UserRegisterRequest", new User());
        }
        modelAndView.setViewName("register");

        return ResponseEntity.ok(userService.register(userRegisterRequest));
    }
}
