package ma.ensa.test_stage_projet.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateUtilisateurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseUtilisateurDTO;
import ma.ensa.test_stage_projet.entities.Utilisateur;
import ma.ensa.test_stage_projet.events.OnRegistrationCompleteEvent;
import ma.ensa.test_stage_projet.exceptions.*;
import ma.ensa.test_stage_projet.mappers.UtilisatuerMapper;
import ma.ensa.test_stage_projet.repositories.UtilisateurRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final UtilisateurRepository utilisateurRepository;
    private final UtilisatuerMapper utilisatuerMapper;
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public ResponseUtilisateurDTO addUtilisateur(CreateUtilisateurDTO createUtilisateurDTO)
            throws NotFoundOperateurException, NotFoundProfileException, DuplicateEmailException {
        Utilisateur utilisateurValidate = utilisateurRepository.findByEmail(createUtilisateurDTO.email());
        if(utilisateurValidate != null) throw new DuplicateEmailException("Email already exists");
        Utilisateur utilisateur = utilisatuerMapper.fromCreate(createUtilisateurDTO);
        String token = utilisateur.generateToken();
        utilisateur.setTokenActivation(token);
        utilisateur.setExpirationDateActivationToken(utilisateur.generateExpirationDate());
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
//        emailService.SendEmail(utilisateur.getEmail(),"Activation Token" ,"your token is :\n " + token);
        applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(this, savedUtilisateur));
        return utilisatuerMapper.toResponse(savedUtilisateur);
    }
    //authentication.name veut ce que renvoie UserDetails.getUsername() dans ce cas c'est email
    @PreAuthorize("@securityEvaluator.isOwnerOrAdmin(" +
            "@utilisateurServiceImpl.loadUtilisateur(#id).email, authentication)")
    @Transactional
    @Override
    public ResponseUtilisateurDTO updateUtilisateur(Long id, CreateUtilisateurDTO createUtilisateurDTO) {
        return null;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public void deleteUtilisateur(Long id)  {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new NotFoundUtilisateur("user not found with id :" + id));
        if (utilisateur.getOperateur() == null) throw new NotUtilisatuerException("this is not an User with id: " + id);
        System.out.println("Deleting user: " + utilisateur.getId() + " - " + utilisateur.getEmail());
//        utilisateur.setProfile(null);
//        utilisateur.setOperateur(null);
//        utilisateur.setSite(null);
        utilisateur.setActive(false);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseUtilisateurDTO getUtilisateur(Long id)  {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new NotFoundUtilisateur("user not found with id :" + id));
        return utilisatuerMapper.toResponse(utilisateur);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<ResponseUtilisateurDTO> getUtilisateurs(int page , int size) {
        Page<Utilisateur> utilisateurs = utilisateurRepository.findAllByOperateurIsNotNull(PageRequest.of(page,size));
        return utilisateurs.stream().map(utilisatuerMapper::toResponse).collect(Collectors.toList());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseUtilisateurDTO getUtilisateurByEmail(String email) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if(utilisateur == null) throw new NotFoundUtilisateur("user not found with email: " + email);
        return utilisatuerMapper.toResponse(utilisateur);
    }

    public Utilisateur loadUtilisateur(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new NotFoundUtilisateur("user not found with id :" + id));
    }

}
