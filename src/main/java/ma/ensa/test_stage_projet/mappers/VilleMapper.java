package ma.ensa.test_stage_projet.mappers;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.VilleDTO;
import ma.ensa.test_stage_projet.entities.ServiceExterieur;
import ma.ensa.test_stage_projet.entities.Ville;
import ma.ensa.test_stage_projet.exceptions.NotFoundSEException;
import ma.ensa.test_stage_projet.repositories.ServiceExterieurRepository;
import ma.ensa.test_stage_projet.repositories.VilleRepositiry;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VilleMapper {
    private final ServiceExterieurRepository serviceExterieurRepository;
    public VilleDTO toVilleDto(Ville ville) {
        VilleDTO villeDTO = new VilleDTO();
        BeanUtils.copyProperties(ville, villeDTO);
        villeDTO.setId_se(villeDTO.getId_se());
        return villeDTO;
    }
    public Ville toVille(VilleDTO villeDTO) throws NotFoundSEException {
        Ville ville = new Ville();
        BeanUtils.copyProperties(villeDTO, ville);
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findById(villeDTO.getId_se()).
                orElseThrow(()->new NotFoundSEException("Service not found"));
        ville.setServiceExterieur(serviceExterieur);
        return ville;
    }
}
