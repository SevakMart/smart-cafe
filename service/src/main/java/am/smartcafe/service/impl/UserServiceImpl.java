package am.smartcafe.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import am.smartcafe.data_access.model.User;
import am.smartcafe.data_access.repository.UserRepository;
import am.smartcafe.service.dto.mapper.UserMapper;
import am.smartcafe.service.dto.resp.UserResponse;
import am.smartcafe.service.UserService;

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
  public UserResponse save(User user) {
    user.setPassword(encoder.encode(user.getPassword()));
    return UserMapper.userToDto(userRepository.save(user));
  }
}
