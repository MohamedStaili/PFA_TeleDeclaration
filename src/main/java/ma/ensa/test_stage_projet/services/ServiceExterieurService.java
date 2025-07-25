package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreateServiceExterieurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseServiceExterieurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseVilleDTO;
import ma.ensa.test_stage_projet.exceptions.AddresseAlreadyADD;
import ma.ensa.test_stage_projet.exceptions.NotFoundSEException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;
import ma.ensa.test_stage_projet.exceptions.VilleNotInSEException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface ServiceExterieurService {
    Map<String,Object> saveExterieur(CreateServiceExterieurDTO serviceExterieurDTO) ;

    @Transactional(rollbackFor = Exception.class)
    Map<String,Object> addAdresse(Long idSE, Long idVille) throws NotFoundSEException , NotFoundVilleException, AddresseAlreadyADD;

    @Transactional(rollbackFor = Exception.class)
    Map<String,Object> updateAddresse(Long idSE, Long idVille) throws NotFoundSEException , NotFoundVilleException, VilleNotInSEException;

    List<ResponseVilleDTO> getServiceExterieurVilles(String nomSE, int page, int size) throws NotFoundSEException;

    ResponseVilleDTO getAddresseSE(String nomSE) throws NotFoundVilleException, NotFoundSEException;

    ResponseServiceExterieurDTO updateSE(CreateServiceExterieurDTO serviceExterieurDTO, Long id) throws NotFoundSEException;
    List<Map<String, Object>> getServiceExterieurDTOs();
    Map<String,Object> getServiceExterieurDTO(Long id) throws NotFoundSEException;

    Map<String, Object> getServiceExterieurByName(String nom) throws NotFoundSEException;
    Map<String, Object> getServiceExterieurDTOByCode(String code) throws NotFoundSEException;

    void deleteServiceExterieur(Long id) throws NotFoundSEException;

    @Transactional(rollbackFor = Exception.class)
    ResponseVilleDTO addVilleToSE(Long idSE, Long idVille) throws NotFoundSEException , NotFoundVilleException;

    @Transactional(rollbackFor = Exception.class)
    ResponseVilleDTO deleteVilleFromSE(Long idSE, Long idVille) throws NotFoundSEException,NotFoundVilleException;

    //void deleteVilleFromSE(Long idSE , Long idVille) throws NotFoundVilleException, NotFoundSEException;
}
