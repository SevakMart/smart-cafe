package am.smartcafe.service;

import java.util.UUID;

public interface MailService {
     void send(String to,String subject,String message);
     String generatePassword();
}
