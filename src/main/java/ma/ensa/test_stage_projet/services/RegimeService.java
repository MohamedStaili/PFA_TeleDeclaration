package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreateRegimeDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseRegimeDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundRegimeException;

import java.util.List;

public interface RegimeService {
    ResponseRegimeDTO findByDesignation(String designation) ;
    ResponseRegimeDTO findByCode(String code) ;
    ResponseRegimeDTO findById(Long id) ;
    ResponseRegimeDTO addRegimeImportation(CreateRegimeDTO createRegimeDTO);
    ResponseRegimeDTO updateRegimeImportation(CreateRegimeDTO createRegimeDTO,Long id) ;
    void deleteRegimeImportation(Long id) ;
    List<ResponseRegimeDTO> findAllRegimes();
}
