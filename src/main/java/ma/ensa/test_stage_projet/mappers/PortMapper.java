package ma.ensa.test_stage_projet.mappers;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreatePortDTO;
import ma.ensa.test_stage_projet.Dtos.ResponsePortDTO;
import ma.ensa.test_stage_projet.entities.PortDechargemnt;
import ma.ensa.test_stage_projet.entities.ServiceExterieur;
import ma.ensa.test_stage_projet.entities.Ville;
import ma.ensa.test_stage_projet.exceptions.NotFoundSEException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;
import ma.ensa.test_stage_projet.repositories.ServiceExterieurRepository;
import ma.ensa.test_stage_projet.repositories.VilleRepositiry;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortMapper {
    private final VilleRepositiry villeRepositiry;
    private final ServiceExterieurRepository serviceExterieurRepository ;

    public PortDechargemnt fromCreate(CreatePortDTO createPortDTO) throws NotFoundSEException, NotFoundVilleException {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findById(createPortDTO.idSE())
                .orElseThrow(() -> new NotFoundSEException("Not found service exterieur"));
        Ville ville = villeRepositiry.findById(createPortDTO.idVille()).orElseThrow(() -> new NotFoundVilleException("Not found ville"));
        PortDechargemnt port = new PortDechargemnt();
        port.setDesignation(createPortDTO.designation());
        port.setCode(createPortDTO.code());
        port.setVille(ville);
        port.setServiceExterieur(serviceExterieur);
        return port;
    }

    public ResponsePortDTO toResponse(PortDechargemnt port) {
        return new ResponsePortDTO(
                port.getId_port(),
                port.getDesignation(),
                port.getCode() ,
                port.getVille().getDesignation(),
                port.getServiceExterieur().getNomSE()
        );
    }
}
