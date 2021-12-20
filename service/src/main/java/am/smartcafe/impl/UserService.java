package am.smartcafe.impl;


import am.smartcafe.model.User;
import am.smartcafe.model.PasswordChangeRequest;

public interface UserService {

    String REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$";


    void saveUser(User user);

    void update(User user);

    User getById(long id);

    void changePassword(PasswordChangeRequest passwordChangeRequest);

}
