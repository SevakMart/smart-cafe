package am.smartcafe.service;


public interface EmailService {

    void sendMessage(String userEmail, String password, long id);

}
