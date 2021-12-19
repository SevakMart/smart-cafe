package am.smartcafe.impl;

import am.smartcafe.model.UserChangePassword;
import am.smartcafe.repository.UserRepository;
import am.smartcafe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;



    private static final String REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$";



    public String changePassword( UserChangePassword userChangePassword){
        User user = getById(userChangePassword.getUserId());
        String failText = "Your password must be at least 6 characters," +
                " and must include at least one upper case letter," +
                " one lower case letter, and one numeric digit.";


        if (passwordEncoder.matches(userChangePassword.getPassword(),user.getPassword()) &&
        userChangePassword.getRepeatNewPassword().equals(userChangePassword.getNewPassword())
          && REGEX.matches(userChangePassword.getNewPassword())){
            String newPassword = passwordEncoder.encode(userChangePassword.getNewPassword());
            user.setPassword(newPassword);
            user.setActive(true);
            userRepository.save(user);
            return null;
        }
        return failText;
    }





    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
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
