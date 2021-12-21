package am.smartcafe.service.impl;

import am.smartcafe.dto.resp.UserResponse;
import am.smartcafe.data_access.model.User;
import am.smartcafe.data_access.repository.UserRepository;
import am.smartcafe.service.UserService;
import am.smartcafe.util.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public UserResponse saveUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return UserMapper.userToDto(userRepository.save(user));
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }
}
