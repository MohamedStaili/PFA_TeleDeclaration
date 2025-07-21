package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.ServiceExterieurDTO;
import ma.ensa.test_stage_projet.Dtos.VilleDTO;
import ma.ensa.test_stage_projet.entities.ServiceExterieur;
import ma.ensa.test_stage_projet.entities.Ville;
import ma.ensa.test_stage_projet.exceptions.NotFoundSEException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;

import java.util.List;

public interface VilleSeService {
    VilleDTO saveVille(VilleDTO villeDTO, String nom_se) throws NotFoundSEException;
     ServiceExterieurDTO saveExterieur(ServiceExterieurDTO serviceExterieurDTO, VilleDTO adresseDTO, List<VilleDTO> villes) throws NotFoundSEException, NotFoundVilleException;
    void deleteVilleFromSE(String nomSE, String nomVille) throws NotFoundVilleException, NotFoundSEException;

    List<VilleDTO> getVilles();

    List<VilleDTO> getServiceExterieurVilles(String nomSE) throws NotFoundSEException;

    VilleDTO getAddresseSE(String nomSE) throws NotFoundVilleException, NotFoundSEException;

    ServiceExterieurDTO updateSE(ServiceExterieurDTO serviceExterieurDTO, Long id) throws NotFoundSEException, NotFoundVilleException;

    VilleDTO updateVille(VilleDTO villeDTO, Long id) throws NotFoundVilleException, NotFoundSEException;

    List<ServiceExterieurDTO> getServiceExterieurDTOs();

    VilleDTO getVilleDTO(Long id) throws NotFoundVilleException;

    VilleDTO getVilleBtName(String nom) throws NotFoundVilleException;

    ServiceExterieurDTO getServiceExterieurDTO(Long id) throws NotFoundSEException;

    ServiceExterieurDTO getServiceExterieurByName(String nom) throws NotFoundSEException;

    void deleteVille(Long id) throws NotFoundVilleException;
}
