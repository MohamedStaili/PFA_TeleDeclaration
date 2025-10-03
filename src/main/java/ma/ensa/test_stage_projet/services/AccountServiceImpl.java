package ma.ensa.test_stage_projet.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.ResponseUtilisateurDTO;
import ma.ensa.test_stage_projet.entities.Utilisateur;
import ma.ensa.test_stage_projet.exceptions.NotFoundUtilisateur;
import ma.ensa.test_stage_projet.exceptions.TokenExperedException;
import ma.ensa.test_stage_projet.repositories.UtilisateurRepository;
import ma.ensa.test_stage_projet.sec.PasswordEncoderConfig;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoderConfig passwordEncoderConfig ;
    @Transactional
    @Override
    public void activateUtilisateur(String token, String Password) {
        Utilisateur utilisateur = utilisateurRepository.findByTokenActivation(token);
        if (utilisateur == null) throw new NotFoundUtilisateur("not found utilisateur with token: " + token);
        if(utilisateur.getExpirationDateActivationToken()!=null
        && utilisateur.getExpirationDateActivationToken().before(new Date())){
            throw new TokenExperedException("expired token");
        }
        utilisateur.setPassword(passwordEncoderConfig.passwordEncoder().encode(Password));
        utilisateur.setTokenActivation(null);
        utilisateur.setExpirationDateActivationToken(null);
        utilisateur.setActive(true);
        utilisateurRepository.save(utilisateur);
    }

}
