package am.smartcafe.service.email;

public interface EmailService {

  void sendMessage(String userEmail, String password, long id);
}
