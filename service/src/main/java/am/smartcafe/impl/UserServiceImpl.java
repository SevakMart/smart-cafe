package am.smartcafe.impl;

import am.smartcafe.model.User;
import am.smartcafe.model.PasswordChangeRequest;
import am.smartcafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;






    public void changePassword(PasswordChangeRequest passwordChangeRequest){
        User user = getById(passwordChangeRequest.getUserId());
        String newPassword = passwordEncoder.encode(passwordChangeRequest.getNewPassword());
        user.setPassword(newPassword);
        user.setActive(true);
        userRepository.save(user);
    }






    @Override
    public void saveUser(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public User getById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
