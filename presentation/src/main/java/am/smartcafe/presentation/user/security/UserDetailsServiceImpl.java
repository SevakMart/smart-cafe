package am.smartcafe.presentation.user.security;

import am.smartcafe.data_access.user.model.User;
import am.smartcafe.data_access.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User byEmail = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("There is no user by this" + email));
        return new CurrentUser(byEmail);
    }


}
