package am.smartcafe.service.impl;

import am.smartcafe.data_access.model.User;
import am.smartcafe.data_access.repository.UserRepository;
import am.smartcafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository  userRepository;

    public void saveUser(User user){
        userRepository.save(user);

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
