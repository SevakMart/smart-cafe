package am.smartcafe.service;

import am.smartcafe.data_access.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    void saveUser(User user);
    Optional<User> findByEmail(String email);
}
