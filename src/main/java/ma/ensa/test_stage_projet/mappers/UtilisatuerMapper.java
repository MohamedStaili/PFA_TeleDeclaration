package ma.ensa.test_stage_projet.mappers;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateUtilisateurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseUtilisateurDTO;
import ma.ensa.test_stage_projet.entities.Operateur;
import ma.ensa.test_stage_projet.entities.Profile;
import ma.ensa.test_stage_projet.entities.Utilisateur;
import ma.ensa.test_stage_projet.exceptions.NotFoundOperateurException;
import ma.ensa.test_stage_projet.exceptions.NotFoundProfileException;
import ma.ensa.test_stage_projet.repositories.OperateurRepository;
import ma.ensa.test_stage_projet.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtilisatuerMapper {
    private final OperateurRepository operateurRepository;
    private final ProfileRepository profileRepository;
    public Utilisateur fromCreate(CreateUtilisateurDTO dto) throws NotFoundOperateurException, NotFoundProfileException {
        Operateur operateur = operateurRepository.findById(dto.operateurId())
                .orElseThrow(() -> new NotFoundOperateurException("Operateur Not Found"));
        Profile profile = profileRepository.findById(operateur.getProfil())
                .orElseThrow(() -> new NotFoundProfileException("Profile Not Found"));
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setOperateur(operateur);
        utilisateur.setEmail(dto.email());
        utilisateur.setProfile(profile);
        return utilisateur;
    }

    public ResponseUtilisateurDTO toResponse(Utilisateur utilisateur) {
        return new ResponseUtilisateurDTO(
                utilisateur.getId() ,
                utilisateur.getEmail() ,
                utilisateur.isActive() ,
                utilisateur.getOperateur().getId_operateur(),
                utilisateur.getProfile().getNom()
        );
    }
}
