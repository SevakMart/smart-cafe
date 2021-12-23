package am.smartcafe.service.user.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import am.smartcafe.data_access.user.model.Role;
import am.smartcafe.data_access.user.model.User;
import am.smartcafe.data_access.user.repository.UserRepository;
import am.smartcafe.service.user.dto.req.PwdChangeRequest;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  private static final String TEST_NEW_PASSWORD = "newPassword";
  @Mock private UserRepository userRepository;
  @Spy private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @InjectMocks private UserServiceImpl userService;

  @Test
  void changePassword() {
    // given
    User user = prepareTestUser();
    PwdChangeRequest pwdChangeRequest = prepareTestPwdChangeRequest(user);
    when(userRepository.save(Mockito.any())).thenReturn(user);
    when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user));
    when(passwordEncoder.encode(anyString())).thenCallRealMethod();

    // when
    userService.changePassword(pwdChangeRequest);

    // then
    verify(passwordEncoder, times(1)).encode(anyString());
    Assertions.assertTrue(passwordEncoder.matches(TEST_NEW_PASSWORD, user.getPassword()));
    verify(userRepository).save(any());
    Assertions.assertTrue(user.isActive());
  }

  private PwdChangeRequest prepareTestPwdChangeRequest(User user) {
    PwdChangeRequest pwdChangeRequest = new PwdChangeRequest();
    pwdChangeRequest.setUserId(1);
    pwdChangeRequest.setPassword(user.getPassword());
    pwdChangeRequest.setNewPassword(TEST_NEW_PASSWORD);
    pwdChangeRequest.setRepeatNewPassword(TEST_NEW_PASSWORD);
    return pwdChangeRequest;
  }

  private User prepareTestUser() {
    User user = new User();
    user.setId(3L);
    user.setFirstName("test");
    user.setEmail("test@gmail.com");
    user.setPassword("test");
    user.setRole(Role.WAITER);
    return user;
  }
}
