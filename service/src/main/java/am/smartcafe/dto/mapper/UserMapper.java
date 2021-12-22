package am.smartcafe.dto.mapper;

import am.smartcafe.data_access.model.User;
import am.smartcafe.dto.req.UserRegisterRequest;
import am.smartcafe.dto.resp.UserResponse;

public class UserMapper {

  private UserMapper() {
    throw new IllegalStateException("Can not create an object of utility class.");
  }

  public static User dtoToUser(UserRegisterRequest request) {
    User user = new User();
    user.setEmail(request.getEmail());
    user.setLastName(request.getLastName());
    user.setFirstName(request.getFirstName());
    user.setPassword(request.getPassword());
    user.setRole(request.getRole());

    return user;
  }

  public static UserResponse userToDto(User user) {
    UserResponse response = new UserResponse();
    response.setFirstName(user.getFirstName());
    response.setLastName(user.getLastName());
    response.setEmail(user.getEmail());
    response.setRole(user.getRole());

    return response;
  }
}
