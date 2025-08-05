package ma.ensa.test_stage_projet.repositories;

import ma.ensa.test_stage_projet.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByEmail(String email);
    Utilisateur findByTokenActivation(String tokenActivation);
    Utilisateur findUtilisateurByPasswordResetToken(String tokenResetToken);
    //Utilisateur findByEmailAndTokenActivation(String email, String tokenActivation);
    //Utilisateur findUtilisateurByPasswordResetTokenAndEmail(String email , String passwordResetToken);
}
