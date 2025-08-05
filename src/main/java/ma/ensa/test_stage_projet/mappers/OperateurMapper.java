package ma.ensa.test_stage_projet.mappers;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateOperatuerDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseOperateurDTO;
import ma.ensa.test_stage_projet.entities.Operateur;
import ma.ensa.test_stage_projet.entities.Profile;
import ma.ensa.test_stage_projet.entities.Ville;
import ma.ensa.test_stage_projet.exceptions.NotFoundProfileException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;
import ma.ensa.test_stage_projet.repositories.OperateurRepository;
import ma.ensa.test_stage_projet.repositories.ProfileRepository;
import ma.ensa.test_stage_projet.repositories.VilleRepositiry;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OperateurMapper {
    private final VilleRepositiry villeRepositiry;
    private final ProfileRepository profileRepository;

    public Operateur fromCreate(CreateOperatuerDTO createOperatuerDTO) throws NotFoundProfileException, NotFoundVilleException {
        Profile profile =profileRepository.findById(createOperatuerDTO.profil())
                .orElseThrow(() -> new NotFoundProfileException("not found Profile"));
        Ville ville = villeRepositiry.findById(createOperatuerDTO.villeID())
                .orElseThrow(() -> new NotFoundVilleException("not found Ville"));
        Operateur operateur = new Operateur();
//        String profileFirstLettre = String.valueOf(profile.getNom().trim().charAt(0));
//        operateur.setCode_cptable(generateNextCode());
//        operateur.setCode(profileFirstLettre + generateNextCode());
        operateur.setActif(1L);
        operateur.setVille(ville);
        operateur.setProfil(profile.getId());
        operateur.setAdresse(createOperatuerDTO.addresse());
        operateur.setIce(createOperatuerDTO.ice());
        operateur.setNum_patenete(createOperatuerDTO.numPatente());
        operateur.setRaison_soc(createOperatuerDTO.raisonSoc());
        operateur.setRegistre_com(createOperatuerDTO.registreCom());
        return operateur ;
    }

    public ResponseOperateurDTO toResponse(Operateur operateur) {
        return new ResponseOperateurDTO(
                operateur.getId_operateur(),
                operateur.getCode(),
                operateur.getRaison_soc(),
                operateur.getCodeCptable(),
                operateur.getActif(),
                operateur.getProfil(),
                operateur.getNum_patenete(),
                operateur.getRegistre_com(),
                operateur.getAdresse(),
                operateur.getIce(),
                operateur.getVille().getDesignation()
        );
    }


}
