package am.smartcafe.util;

import am.smartcafe.data_access.dto.req.UserRegisterRequest;
import am.smartcafe.data_access.dto.resp.UserRegisterResponse;
import am.smartcafe.data_access.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserUtil {

    public static User dtoToUser(UserRegisterRequest request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .build();
    }

    public static UserRegisterResponse userToDto(User user) {
        return UserRegisterResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}

