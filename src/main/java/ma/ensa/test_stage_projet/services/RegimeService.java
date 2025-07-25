package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreateRegimeDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseRegimeDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundRegimeException;

import java.util.List;

public interface RegimeService {
    ResponseRegimeDTO findByDesignation(String designation) throws NotFoundRegimeException;
    ResponseRegimeDTO findByCode(String code) throws NotFoundRegimeException;
    ResponseRegimeDTO findById(Long id) throws NotFoundRegimeException;
    ResponseRegimeDTO addRegimeImportation(CreateRegimeDTO createRegimeDTO);
    ResponseRegimeDTO updateRegimeImportation(CreateRegimeDTO createRegimeDTO,Long id) throws NotFoundRegimeException;
    void deleteRegimeImportation(Long id) throws NotFoundRegimeException;
    List<ResponseRegimeDTO> findAllRegimes();
}
