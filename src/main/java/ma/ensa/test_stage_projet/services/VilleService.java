package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.ServiceExterieurDTO;
import ma.ensa.test_stage_projet.Dtos.VilleDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundSEException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;

import java.util.List;

public interface VilleService {
    VilleDTO saveVille(VilleDTO villeDTO, String nom_se) throws NotFoundSEException;
     ServiceExterieurDTO saveExterieur(ServiceExterieurDTO serviceExterieurDTO, VilleDTO adresseDTO, List<VilleDTO> villes) throws NotFoundSEException, NotFoundVilleException;
    void deleteVilleFromSE(String nomSE, String nomVille) throws NotFoundVilleException, NotFoundSEException;

    List<VilleDTO> getVilles(int page,int size);

    VilleDTO updateVille(VilleDTO villeDTO, Long id) throws NotFoundVilleException, NotFoundSEException;

    VilleDTO getVilleDTO(Long id) throws NotFoundVilleException;

    VilleDTO getVilleByName(String nom) throws NotFoundVilleException;

    void deleteVille(Long id) throws NotFoundVilleException;

    VilleDTO getVilleByCode(String code) throws NotFoundVilleException;
}
