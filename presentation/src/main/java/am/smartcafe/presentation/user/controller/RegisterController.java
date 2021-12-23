package am.smartcafe.presentation.user.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import am.smartcafe.data_access.user.model.User;
import am.smartcafe.service.user.UserService;
import am.smartcafe.service.user.dto.mapper.UserMapper;
import am.smartcafe.service.user.dto.req.UserRegisterRequest;
import am.smartcafe.service.user.dto.resp.UserResponse;

@Controller
public class RegisterController {

  private final UserService userService;

  public RegisterController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/register")
  public String userPage(ModelMap modelMap) {
    modelMap.addAttribute("");
    return "register";
  }

  @PostMapping("/register")
  public String createNewUser(
      @Valid @ModelAttribute UserRegisterRequest userRegisterRequest,
      ModelMap modelMap,
      BindingResult bindingResult) {
    Optional<User> userExists = userService.findByEmail(userRegisterRequest.getEmail());
    if (userExists.isPresent()) {
      bindingResult.rejectValue(
          "email",
          "error.user",
          "User with this email already exists. Login or reset your password.");
    }
    if (bindingResult.hasErrors()) {
      return "redirect:/register";
    }
    UserResponse userResponse = userService.save(UserMapper.dtoToUser(userRegisterRequest));
    modelMap.addAttribute("user", userResponse);
    return "redirect:/?msg= User was added";
  }
}
