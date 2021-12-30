package am.smartcafe.service.user.impl;

import java.util.Optional;

import am.smartcafe.service.exception.ModelAlreadyExistException;
import am.smartcafe.service.user.dto.req.UserRegisterRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import am.smartcafe.data_access.user.model.User;
import am.smartcafe.data_access.user.repository.UserRepository;
import am.smartcafe.service.user.UserService;
import am.smartcafe.service.user.dto.mapper.UserMapper;
import am.smartcafe.service.user.dto.req.PwdChangeRequest;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder encoder;

  public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
    this.userRepository = userRepository;
    this.encoder = encoder;
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public void save(UserRegisterRequest userRegisterRequest) throws ModelAlreadyExistException {
    if (isUserExist(userRegisterRequest.getEmail())) {
      throw new ModelAlreadyExistException(
          String.format("User with '%s' email was already exist.",
                  userRegisterRequest.getEmail()));
    }
    userRegisterRequest.setPassword(encoder.encode(userRegisterRequest.getPassword()));
    UserMapper.userToDto(userRepository.save(new User()));
  }

  public boolean isUserExist(String email) {
    return userRepository.findByEmail(email).isPresent();
  }

  public void changePassword(PwdChangeRequest pwdChangeRequest) {
    User user = getById(pwdChangeRequest.getUserId());
    String newPassword = encoder.encode(pwdChangeRequest.getNewPassword());
    user.setPassword(newPassword);
    user.setActive(true);
    userRepository.save(user);
  }

  @Override
  public User getById(long id) {
    return userRepository
        .findById(id)
        .orElseThrow(() -> new UsernameNotFoundException("user not found"));
  }
}
