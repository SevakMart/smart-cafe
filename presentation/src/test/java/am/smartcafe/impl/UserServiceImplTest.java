package am.smartcafe.impl;

import am.presentation.SmartCafeApplication;
import am.smartcafe.model.PasswordChangeRequest;
import am.smartcafe.model.Role;
import am.smartcafe.model.User;
import am.smartcafe.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = SmartCafeApplication.class)
class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void changePassword() {
        User user = new User();
        user.setId(3L);
        user.setFirstName("test");
        user.setEmail("test@gmail.com");
        user.setPassword("test");
        user.setRole(Role.WAITER);

        PasswordChangeRequest passwordChangeRequest = new PasswordChangeRequest();
        passwordChangeRequest.setUserId(1);
        passwordChangeRequest.setPassword(user.getPassword());
        passwordChangeRequest.setNewPassword("newPassword");
        passwordChangeRequest.setRepeatNewPassword("newPassword");

        when(userRepository.save(Mockito.any())).thenReturn(user);
        when(userRepository.findById(Mockito.any())).thenReturn(java.util.Optional.of(user));
        userService.changePassword(passwordChangeRequest);
        Assertions.assertTrue(passwordEncoder.matches("newPassword",user.getPassword()));
        verify(userRepository).save(any());
        assertTrue(user.isActive());
    }
}

