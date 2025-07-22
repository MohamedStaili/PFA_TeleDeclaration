package ma.ensa.test_stage_projet.mappers;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.ServiceExterieurDTO;
import ma.ensa.test_stage_projet.entities.ServiceExterieur;
import ma.ensa.test_stage_projet.entities.Ville;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;
import ma.ensa.test_stage_projet.repositories.VilleRepositiry;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceExterieurMapper {
    private final VilleRepositiry villeRepositiry;
    public ServiceExterieurDTO toServiceExterieurDTO(ServiceExterieur serviceExterieur) {
        return new ServiceExterieurDTO(
                serviceExterieur.getId_se(),
                serviceExterieur.getNomSE(),
                serviceExterieur.getCodeSE(),
                serviceExterieur.getAdresse().getId_ville()
        );
    }
    public ServiceExterieur toServiceExterieur(ServiceExterieurDTO dto) throws NotFoundVilleException {
        Ville ville = villeRepositiry.findById(dto.ville_id()).orElseThrow(()->new NotFoundVilleException("Ville not found"));
        ServiceExterieur serviceExterieur = new ServiceExterieur();
        serviceExterieur.setId_se(dto.id_se());
        serviceExterieur.setNomSE(dto.nomSE());
        serviceExterieur.setCodeSE(dto.code());
        serviceExterieur.setAdresse(ville);
        return serviceExterieur;
    }
}
