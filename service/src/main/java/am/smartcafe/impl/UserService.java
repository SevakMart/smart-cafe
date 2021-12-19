package am.smartcafe.impl;


import am.smartcafe.model.User;
import am.smartcafe.model.UserChangePassword;

public interface UserService {

    void saveUser(User user);

    void update(User user);

    User getById(long id);

    String changePassword(UserChangePassword userChangePassword);

}
