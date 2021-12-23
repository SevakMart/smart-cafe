package am.smartcafe.service.email;

import java.util.UUID;

public interface MailService {
     void send(String to,String subject,String message);
     String generatePassword();
}
