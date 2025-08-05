package ma.ensa.test_stage_projet.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateUtilisateurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseUtilisateurDTO;
import ma.ensa.test_stage_projet.entities.Utilisateur;
import ma.ensa.test_stage_projet.events.OnRegistrationCompleteEvent;
import ma.ensa.test_stage_projet.exceptions.NotFoundOperateurException;
import ma.ensa.test_stage_projet.exceptions.NotFoundProfileException;
import ma.ensa.test_stage_projet.mappers.UtilisatuerMapper;
import ma.ensa.test_stage_projet.repositories.UtilisateurRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final UtilisateurRepository utilisateurRepository;
    private final UtilisatuerMapper utilisatuerMapper;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseUtilisateurDTO addUtilisateur(CreateUtilisateurDTO createUtilisateurDTO) throws NotFoundOperateurException, NotFoundProfileException {
        Utilisateur utilisateur = utilisatuerMapper.fromCreate(createUtilisateurDTO);
        String token = UUID.randomUUID().toString();
        utilisateur.setTokenActivation(token);
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
//        emailService.SendEmail(utilisateur.getEmail(),"Activation Token" ,"your token is :\n " + token);
        applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(this, savedUtilisateur));
        return utilisatuerMapper.toResponse(savedUtilisateur);
    }

    @Override
    public void activateUtilisateur(String Password) {

    }

    @Override
    public ResponseUtilisateurDTO updateUtilisateur(Long id, CreateUtilisateurDTO createUtilisateurDTO) {
        return null;
    }

    @Override
    public void deleteUtilisateur(Long id) {

    }

    @Override
    public ResponseUtilisateurDTO getUtilisateur(Long id) {
        return null;
    }

    @Override
    public List<ResponseUtilisateurDTO> getUtilisateurs() {
        return List.of();
    }

    @Override
    public ResponseUtilisateurDTO getUtilisateurByEmail(String email) {
        return null;
    }

    @Override
    public ResponseUtilisateurDTO getUtilisateurByActivationToken(String activationToken) {
        return null;
    }
}
