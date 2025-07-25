package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreatePortDTO;
import ma.ensa.test_stage_projet.Dtos.ResponsePortDTO;
import ma.ensa.test_stage_projet.entities.PortDechargemnt;
import ma.ensa.test_stage_projet.exceptions.NotFoundPortException;
import ma.ensa.test_stage_projet.exceptions.NotFoundSEException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;

import java.util.List;

public interface PortService {
    ResponsePortDTO addPort(CreatePortDTO createPortDTO) throws NotFoundSEException, NotFoundVilleException;
    ResponsePortDTO updatePort(CreatePortDTO createPortDTO , Long id) throws NotFoundPortException, NotFoundSEException, NotFoundVilleException;
    void deletePort(Long idPort) throws NotFoundPortException;
    ResponsePortDTO getPort(Long idPort) throws NotFoundPortException;
    List<ResponsePortDTO> getPorts();
}
