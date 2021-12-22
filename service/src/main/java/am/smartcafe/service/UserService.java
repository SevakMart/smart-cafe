package am.smartcafe.service;

import java.util.Optional;

import am.smartcafe.data_access.model.User;
import am.smartcafe.service.dto.resp.UserResponse;

public interface UserService {
  UserResponse save(User user);

  Optional<User> findByEmail(String email);
}
