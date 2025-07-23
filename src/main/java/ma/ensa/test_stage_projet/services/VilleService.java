package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreateVilleDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseVilleDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundSEException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;

import java.util.Map;

public interface VilleService {
    ResponseVilleDTO saveVille(CreateVilleDTO villeDTO) throws NotFoundSEException;
     void deleteVilleFromSE(String nomSE, String nomVille) throws NotFoundVilleException, NotFoundSEException;

     Map<String,Object> getVilles(int page, int size);

    ResponseVilleDTO updateVille(CreateVilleDTO villeDTO, Long id) throws NotFoundVilleException, NotFoundSEException;

    ResponseVilleDTO getVilleDTO(Long id) throws NotFoundVilleException;

    ResponseVilleDTO getVilleByName(String nom) throws NotFoundVilleException;

    void deleteVille(Long id) throws NotFoundVilleException;

    ResponseVilleDTO getVilleByCode(String code) throws NotFoundVilleException;
}
