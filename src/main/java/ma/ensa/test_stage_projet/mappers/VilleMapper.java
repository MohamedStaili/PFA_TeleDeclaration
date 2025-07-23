package ma.ensa.test_stage_projet.mappers;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateVilleDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseVilleDTO;
import ma.ensa.test_stage_projet.entities.ServiceExterieur;
import ma.ensa.test_stage_projet.entities.Ville;
import ma.ensa.test_stage_projet.exceptions.NotFoundSEException;
import ma.ensa.test_stage_projet.repositories.ServiceExterieurRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VilleMapper {
    private final ServiceExterieurRepository serviceExterieurRepository;

    public Ville fromCreate(CreateVilleDTO createVilleDTO) {
        ServiceExterieur serviceExterieur =serviceExterieurRepository.findByNomSE(createVilleDTO.nomSE());
        Ville ville = new Ville();
        ville.setDesignation(createVilleDTO.designation());
        ville.setCode(createVilleDTO.code());
        ville.setServiceExterieur(serviceExterieur);
        return ville;
    }
//    public Ville fromResponse(ResponseVilleDTO responseVilleDTO) {
//        ServiceExterieur serviceExterieur =serviceExterieurRepository.findByNomSE(responseVilleDTO.serviceExterieur());
//        Ville ville = new Ville();
//        ville.setId_ville(responseVilleDTO.id());
//        ville.setDesignation(responseVilleDTO.designation());
//        ville.setCode(responseVilleDTO.code());
//        ville.setServiceExterieur(serviceExterieur);
//        return ville;
//    }
//    public CreateVilleDTO toCreateVilleDTO(Ville ville) {
//        return new CreateVilleDTO(
//                ville.getDesignation(),
//                ville.getCode(),
//                ville.getServiceExterieur().getNomSE()
//        );
//    }

    public ResponseVilleDTO toResponse(Ville ville) {
        return new ResponseVilleDTO(
                ville.getId_ville(),
                ville.getDesignation(),
                ville.getCode(),
                ville.getServiceExterieur().getNomSE()
        );
    }
}
