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
        ServiceExterieurDTO dto = new ServiceExterieurDTO();
        BeanUtils.copyProperties(serviceExterieur, dto);
        dto.setVille_id(serviceExterieur.getAdresse().getId_ville());
        return dto;
    }
    public ServiceExterieur toServiceExterieur(ServiceExterieurDTO dto) throws NotFoundVilleException {
        Ville ville = villeRepositiry.findById(dto.getVille_id()).orElseThrow(()->new NotFoundVilleException("Ville not found"));
        ServiceExterieur serviceExterieur = new ServiceExterieur();
        BeanUtils.copyProperties(dto, serviceExterieur);
        serviceExterieur.setAdresse(ville);
        return serviceExterieur;
    }
}
