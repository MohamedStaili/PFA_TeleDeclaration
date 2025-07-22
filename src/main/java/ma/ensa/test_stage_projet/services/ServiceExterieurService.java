package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.ServiceExterieurDTO;
import ma.ensa.test_stage_projet.Dtos.VilleDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundSEException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;

import java.util.List;

public interface ServiceExterieurService {
    List<VilleDTO> getServiceExterieurVilles(String nomSE, int page, int size) throws NotFoundSEException;

    VilleDTO getAddresseSE(String nomSE) throws NotFoundVilleException, NotFoundSEException;

    ServiceExterieurDTO updateSE(ServiceExterieurDTO serviceExterieurDTO, Long id) throws NotFoundSEException, NotFoundVilleException;
    List<ServiceExterieurDTO> getServiceExterieurDTOs();
    ServiceExterieurDTO getServiceExterieurDTO(Long id) throws NotFoundSEException;

    ServiceExterieurDTO getServiceExterieurByName(String nom) throws NotFoundSEException;
    ServiceExterieurDTO getServiceExterieurDTOByCode(String code) throws NotFoundSEException;
}
