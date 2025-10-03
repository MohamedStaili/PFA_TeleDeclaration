package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreatePortDTO;
import ma.ensa.test_stage_projet.Dtos.ResponsePortDTO;
import ma.ensa.test_stage_projet.entities.PortDechargemnt;
import ma.ensa.test_stage_projet.exceptions.NotFoundPortException;
import ma.ensa.test_stage_projet.exceptions.NotFoundSEException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;

import java.util.List;

public interface PortService {
    ResponsePortDTO addPort(CreatePortDTO createPortDTO) ;
    ResponsePortDTO updatePort(CreatePortDTO createPortDTO , Long id) ;
    void deletePort(Long idPort) ;
    ResponsePortDTO getPort(Long idPort) ;
    List<ResponsePortDTO> getPorts();
}
