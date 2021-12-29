package am.smartcafe.presentation.user.controller;

import am.smartcafe.data_access.user.model.User;
import am.smartcafe.service.user.UserService;
import am.smartcafe.service.user.dto.req.UserRegisterRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    UserRegisterRequest user = new UserRegisterRequest();
    modelAndView.addObject("userDto", user);
    modelAndView.setViewName("register");
    return modelAndView;
  }

  @PostMapping("/register")
  public ModelAndView createNewUser(final UserRegisterRequest userRequest,
                                    BindingResult bindingResult) {
    ModelAndView modelAndView = new ModelAndView();
    Optional<User> userExists = userService.findByEmail(userRequest.getEmail());
    if (userExists.isPresent()) {
      bindingResult
              .rejectValue("email", "error.user",
                      "There is already a user registered with the email provided");
    }
    if (bindingResult.hasErrors()) {
      modelAndView.setViewName("register");
    } else {
      userService.save(userRequest);
      modelAndView.addObject("successMessage", "User has been registered successfully");
      modelAndView.addObject("user", new User());
    }
    modelAndView.setViewName("register");
    return modelAndView;
  }
}
