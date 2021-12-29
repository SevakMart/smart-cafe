package am.smartcafe.service.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender emailSender;

  public EmailServiceImpl(JavaMailSender emailSender) {
    this.emailSender = emailSender;
  }

  public void sendMessage(String userEmail, String password, long id) {
    String text =
        "Dear user use this is your password :  "
            + password
            + "\n"
            + "Dear user use the following link to change your password \n"
            + "<a href=\"http://localhost:8080/user/changePassword?userId="
            + id
            + "\"a>Change Password</a>";
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("noreply@smartkitchen.com");
    message.setTo(userEmail);
    message.setSubject("Change Password");
    message.setText(text);
    emailSender.send(message);
  }
}
