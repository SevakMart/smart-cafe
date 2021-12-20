package am.smartcafe.service;

import am.smartcafe.data_access.dto.req.UserRegisterRequest;
import am.smartcafe.data_access.dto.resp.UserRegisterResponse;
import am.smartcafe.data_access.model.User;

import am.smartcafe.presentation.exception.ModelAlreadyExistException;
import am.smartcafe.presentation.repository.UserRepository;
import am.smartcafe.util.UserUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean isUserExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) throws ModelAlreadyExistException {
        if (isUserExist(userRegisterRequest.getEmail())) {
            throw new ModelAlreadyExistException("User with '%s' email was already exist."
                    + userRegisterRequest.getEmail() + "Login or reset your password.");
        }
        userRegisterRequest.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        User user = UserUtil.dtoToUser(userRegisterRequest);
        User savedUser = userRepository.save(user);
        return UserUtil.userToDto(savedUser);
    }
}
