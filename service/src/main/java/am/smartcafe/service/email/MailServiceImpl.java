package am.smartcafe.service.email;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MailServiceImpl implements MailService {
    private final MailSender mailSender;

    public MailServiceImpl(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void send(String to, String subject, String message) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        Thread thread = new Thread(() -> mailSender.send(simpleMailMessage));
        thread.start();
    }

    @Override
    public String generatePassword() {
        UUID uuid = UUID.randomUUID();
        String password = String.valueOf(uuid);
        return password.substring(0, 8);
    }

}