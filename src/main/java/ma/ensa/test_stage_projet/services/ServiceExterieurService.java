package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreateServiceExterieurDTO;
import ma.ensa.test_stage_projet.Dtos.CreateVilleDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseServiceExterieurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseVilleDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundSEException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;

import java.util.List;

public interface ServiceExterieurService {
    ResponseServiceExterieurDTO saveExterieur(CreateServiceExterieurDTO serviceExterieurDTO, CreateVilleDTO adresseDTO, List<CreateVilleDTO> villes) throws NotFoundSEException, NotFoundVilleException;

    List<ResponseVilleDTO> getServiceExterieurVilles(String nomSE, int page, int size) throws NotFoundSEException;

    ResponseVilleDTO getAddresseSE(String nomSE) throws NotFoundVilleException, NotFoundSEException;

    ResponseServiceExterieurDTO updateSE(CreateServiceExterieurDTO serviceExterieurDTO, Long id) throws NotFoundSEException, NotFoundVilleException;
    List<ResponseServiceExterieurDTO> getServiceExterieurDTOs();
    ResponseServiceExterieurDTO getServiceExterieurDTO(Long id) throws NotFoundSEException;

    ResponseServiceExterieurDTO getServiceExterieurByName(String nom) throws NotFoundSEException;
    ResponseServiceExterieurDTO getServiceExterieurDTOByCode(String code) throws NotFoundSEException;

    void deleteServiceExterieur(Long id) throws NotFoundSEException;
}
