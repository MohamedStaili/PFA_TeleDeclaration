package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreateServiceExterieurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseServiceExterieurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseVilleDTO;
import ma.ensa.test_stage_projet.exceptions.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface ServiceExterieurService {
    Map<String,Object> saveExterieur(CreateServiceExterieurDTO serviceExterieurDTO) ;

    @Transactional(rollbackFor = Exception.class)
    Map<String,Object> addAdresse(Long idSE, Long idVille) ;

    @Transactional(rollbackFor = Exception.class)
    Map<String,Object> updateAddresse(Long idSE, Long idVille) ;

    List<ResponseVilleDTO> getServiceExterieurVilles(String nomSE, int page, int size) ;

    ResponseVilleDTO getAddresseSE(String nomSE) ;

    ResponseServiceExterieurDTO updateSE(CreateServiceExterieurDTO serviceExterieurDTO, Long id) ;
    List<Map<String, Object>> getServiceExterieurDTOs();
    Map<String,Object> getServiceExterieurDTO(Long id) ;

    Map<String, Object> getServiceExterieurByName(String nom) ;
    Map<String, Object> getServiceExterieurDTOByCode(String code) ;

    void deleteServiceExterieur(Long id);

    @Transactional(rollbackFor = Exception.class)
    ResponseVilleDTO addVilleToSE(Long idSE, Long idVille) ;

    @Transactional(rollbackFor = Exception.class)
    ResponseVilleDTO deleteVilleFromSE(Long idSE, Long idVille) ;

    //void deleteVilleFromSE(Long idSE , Long idVille) throws NotFoundVilleException, NotFoundSEException;
}
