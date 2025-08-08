package ma.ensa.test_stage_projet.mappers;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateAdminDTO;
import ma.ensa.test_stage_projet.Dtos.CreateUtilisateurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseAdminDTO;
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
public class AdminMapper {
    private final OperateurRepository operateurRepository;
    private final ProfileRepository profileRepository;
    public Utilisateur fromCreate(CreateAdminDTO dto){
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail(dto.email());
        return utilisateur;
    }

    public ResponseAdminDTO toResponse(Utilisateur utilisateur) {
        return new ResponseAdminDTO(
                utilisateur.getId() ,
                utilisateur.getEmail() ,
                utilisateur.isActive() ,
                utilisateur.getProfile().getNom()
        );
    }
}
