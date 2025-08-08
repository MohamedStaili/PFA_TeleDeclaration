package ma.ensa.test_stage_projet.repositories;

import ma.ensa.test_stage_projet.entities.Utilisateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Page<Utilisateur> findAllByOperateurIsNotNull(Pageable pageable);
    Utilisateur findByTokenActivation(String tokenActivation);
    Utilisateur findByEmail(String email);
    Utilisateur findUtilisateurByPasswordResetToken(String tokenResetToken);
    Page<Utilisateur> findAllByOperateurIsNull(Pageable pageable);
    //Utilisateur findByEmailAndTokenActivation(String email, String tokenActivation);
    //Utilisateur findUtilisateurByPasswordResetTokenAndEmail(String email , String passwordResetToken);
}
