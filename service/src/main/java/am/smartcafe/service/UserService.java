package am.smartcafe.service;

import java.util.Optional;

import am.smartcafe.data_access.model.User;
import am.smartcafe.service.dto.req.PasswordChangeRequest;
import am.smartcafe.service.dto.resp.UserResponse;

public interface UserService {
  String REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$";


  UserResponse save(User user);

  Optional<User> findByEmail(String email);

  User getById(long id);

  void changePassword(PasswordChangeRequest passwordChangeRequest);

}
