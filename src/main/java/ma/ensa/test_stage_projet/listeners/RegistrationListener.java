package ma.ensa.test_stage_projet.listeners;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.entities.Utilisateur;
import ma.ensa.test_stage_projet.events.OnRegistrationCompleteEvent;
import ma.ensa.test_stage_projet.services.EmailService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationListener {
    private final EmailService emailService;

    @EventListener
    public void handleCreateAccount(OnRegistrationCompleteEvent event) {
        Utilisateur utilisateur = event.getUtilisateur();
        String token = utilisateur.getTokenActivation();
        String email = utilisateur.getEmail();
        String subject = "Activate your account";
        String body = "Copy this code to activate your account : \n" + token ;
        emailService.SendEmail(email, subject, body);
    }
}
