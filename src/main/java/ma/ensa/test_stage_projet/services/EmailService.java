package ma.ensa.test_stage_projet.services;

import jakarta.mail.MessagingException;
import org.thymeleaf.context.Context;

public interface EmailService {
    void SendEmail(String to, String subject, String template , Context context) throws MessagingException;
}
