package ma.ensa.test_stage_projet.listeners;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.entities.Utilisateur;
import ma.ensa.test_stage_projet.events.OnRegistrationCompleteEvent;
import ma.ensa.test_stage_projet.services.EmailService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

@Component
@RequiredArgsConstructor
public class RegistrationListener {
    private final EmailService emailService;

    @EventListener
    public void handleCreateAccount(OnRegistrationCompleteEvent event) {
        Utilisateur utilisateur = event.getUtilisateur();
        String token = utilisateur.getTokenActivation();
        String link = "http://localhost:8080/api/v1/accounts/activate?token=" + token;
        String email = utilisateur.getEmail();
        String subject = "Activate your account";
        Context context = new Context();
        context.setVariable("name",email);
        context.setVariable("link", link);
        context.setVariable("subject", subject);
        try {
            emailService.SendEmail(email, subject, "email" , context);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
