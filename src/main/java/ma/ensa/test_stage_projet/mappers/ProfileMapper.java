package ma.ensa.test_stage_projet.mappers;
import ma.ensa.test_stage_projet.Dtos.CreateProfileDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseProfileDTO;
import ma.ensa.test_stage_projet.entities.Profile;
import org.springframework.stereotype.Service;

@Service

public class ProfileMapper {
    public Profile fromCreate(CreateProfileDTO createProfileDTO){
        Profile profile = new Profile();
        profile.setNom(createProfileDTO.nom());
        profile.setDescription(createProfileDTO.description());
        return profile;
    }

    public ResponseProfileDTO toResponse(Profile profile) {
        return new ResponseProfileDTO(
                profile.getId(),
                profile.getNom(),
                profile.getDescription()
        );
    }

}
