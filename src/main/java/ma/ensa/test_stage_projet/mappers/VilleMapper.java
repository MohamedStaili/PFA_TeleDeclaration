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
        return new VilleDTO(
                ville.getId_ville(),
                ville.getDesignation(),
                ville.getCode(),
                ville.getServiceExterieur().getId_se()
        );

    }
    public Ville toVille(VilleDTO villeDTO) throws NotFoundSEException {
        ServiceExterieur serviceExterieur = serviceExterieurRepository
                .findById(villeDTO.idSE()).orElseThrow(()-> new NotFoundSEException("Service Exterieur not Found"));
        Ville ville = new Ville();
        ville.setId_ville(villeDTO.id());
        ville.setDesignation(villeDTO.designation());
        ville.setCode(villeDTO.code());
        ville.setServiceExterieur(serviceExterieur);
        return ville;
    }
}
