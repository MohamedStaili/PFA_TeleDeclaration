package ma.ensa.test_stage_projet.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateAdminDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseAdminDTO;
import ma.ensa.test_stage_projet.entities.Profile;
import ma.ensa.test_stage_projet.entities.Utilisateur;
import ma.ensa.test_stage_projet.events.OnRegistrationCompleteEvent;
import ma.ensa.test_stage_projet.exceptions.DuplicateEmailException;
import ma.ensa.test_stage_projet.exceptions.NotAdminException;
import ma.ensa.test_stage_projet.exceptions.NotFoundProfileException;
import ma.ensa.test_stage_projet.exceptions.NotFoundUtilisateur;
import ma.ensa.test_stage_projet.mappers.AdminMapper;
import ma.ensa.test_stage_projet.repositories.ProfileRepository;
import ma.ensa.test_stage_projet.repositories.UtilisateurRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final ApplicationEventPublisher applicationEventPublisher;
    private final UtilisateurRepository utilisateurRepository;
    private final ProfileRepository profileRepository;
    private final AdminMapper adminMapper;
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public ResponseAdminDTO addAdmin(CreateAdminDTO createAdminDTO)  {
        Profile profile = profileRepository.findByNom("ADMIN");
        if (profile==null) throw new NotFoundProfileException("Profile non found with name ADMIN");
        Utilisateur utilisateurEmail = utilisateurRepository.findByEmail(createAdminDTO.email());
        if(utilisateurEmail != null) throw new DuplicateEmailException("email already exist");
        Utilisateur utilisateur = adminMapper.fromCreate(createAdminDTO);
        String token = utilisateur.generateToken();
        utilisateur.setTokenActivation(token);
        utilisateur.setProfile(profile);
        utilisateur.setExpirationDateActivationToken(utilisateur.generateExpirationDate());
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        applicationEventPublisher.publishEvent(new OnRegistrationCompleteEvent(this , savedUtilisateur));
        return adminMapper.toResponse(savedUtilisateur);
    }
    @PreAuthorize("@securityEvaluator.isOwnerOrAdmin(" +
            "@utilisateurServiceImpl.loadUtilisateur(#id).email, authentication)")
    @Transactional
    @Override
    public ResponseAdminDTO updateAdmin(Long id, CreateAdminDTO createAdminDTO) {
        return null;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public void deleteAdmin(Long id) throws NotFoundUtilisateur, NotAdminException {
        Utilisateur utilisateur = utilisateurRepository.findById(id)
                .orElseThrow(() -> new NotFoundUtilisateur("user not found with id :" + id));
        if (!Objects.equals(utilisateur.getProfile().getNom(), "ADMIN")) throw new NotAdminException("this is not an Admin with id: " + id);
        System.out.println("Deleting user: " + utilisateur.getId() + " - " + utilisateur.getEmail());
//        utilisateur.setProfile(null);
//        utilisateur.setOperateur(null);
//        utilisateur.setSite(null);
        utilisateurRepository.deleteById(id);

    }
    @PreAuthorize("@securityEvaluator.isOwnerOrAdmin(" +
            "@utilisateurServiceImpl.loadUtilisateur(#id).email, authentication)")
    @Override
    public ResponseAdminDTO getAdmin(Long id) throws NotFoundUtilisateur {
        Utilisateur utilisateur = utilisateurRepository.findById(id).orElseThrow(() -> new NotFoundUtilisateur("user not found with id :" + id));
        return adminMapper.toResponse(utilisateur);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public List<ResponseAdminDTO> getAdmins(int page, int size) {
        Page<Utilisateur> utilisateurs = utilisateurRepository.findAllByOperateurIsNull(PageRequest.of(page,size));
        return utilisateurs.stream().map(adminMapper::toResponse).collect(Collectors.toList());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Override
    public ResponseAdminDTO getAdminByEmail(String email) throws NotFoundUtilisateur {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email);
        if(utilisateur == null) throw new NotFoundUtilisateur("user not found with email: " + email);
        return adminMapper.toResponse(utilisateur);
    }
}
