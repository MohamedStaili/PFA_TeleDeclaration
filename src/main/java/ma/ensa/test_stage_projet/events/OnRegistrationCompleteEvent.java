package ma.ensa.test_stage_projet.events;

import ma.ensa.test_stage_projet.entities.Utilisateur;
import org.springframework.context.ApplicationEvent;


public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private final Utilisateur utilisateur;
    public OnRegistrationCompleteEvent(Object source, Utilisateur utilisateur) {
        super(source);
        this.utilisateur = utilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
}
