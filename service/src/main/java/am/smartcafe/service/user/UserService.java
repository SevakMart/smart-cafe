package am.smartcafe.service.user;

import java.util.Optional;

import am.smartcafe.data_access.user.model.User;
import am.smartcafe.service.user.dto.req.PwdChangeRequest;
import am.smartcafe.service.user.dto.req.UserRegisterRequest;

public interface UserService {
  String REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$";

  void save(UserRegisterRequest user);

  Optional<User> findByEmail(String email);

  User getById(long id);

  void changePassword(PwdChangeRequest pwdChangeRequest);
}
