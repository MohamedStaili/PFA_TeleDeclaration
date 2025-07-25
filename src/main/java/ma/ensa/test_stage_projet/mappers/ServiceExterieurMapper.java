package ma.ensa.test_stage_projet.mappers;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateServiceExterieurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseServiceExterieurDTO;
import ma.ensa.test_stage_projet.entities.ServiceExterieur;
import ma.ensa.test_stage_projet.entities.Ville;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;
import ma.ensa.test_stage_projet.repositories.VilleRepositiry;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceExterieurMapper {
    private final VilleRepositiry villeRepositiry;

    public ServiceExterieur fromCreate(CreateServiceExterieurDTO createServiceExterieurDTO) {
        ServiceExterieur serviceExterieur = new ServiceExterieur();
        serviceExterieur.setCodeSE(createServiceExterieurDTO.code());
        serviceExterieur.setNomSE(createServiceExterieurDTO.nomSE());
        return serviceExterieur;
    }

    public ResponseServiceExterieurDTO toResponse(ServiceExterieur serviceExterieur) {
        return new ResponseServiceExterieurDTO(
                serviceExterieur.getIdSE(),
                serviceExterieur.getNomSE(),
                serviceExterieur.getCodeSE(),
                serviceExterieur.getAdresse() ==null ? "pas d'adresse" : serviceExterieur.getAdresse().getDesignation()
        );
    }
}
