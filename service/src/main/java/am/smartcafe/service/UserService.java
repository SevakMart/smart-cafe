package am.smartcafe.service;

import am.smartcafe.dto.resp.UserResponse;
import am.smartcafe.data_access.model.User;

import java.util.Optional;

public interface UserService {
  UserResponse saveUser(User user);

  Optional<User> findByEmail(String email);
}
