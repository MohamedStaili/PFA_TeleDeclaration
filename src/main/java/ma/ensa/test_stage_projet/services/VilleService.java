package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreateVilleDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseVilleDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundSEException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;

import java.util.Map;

public interface VilleService {
    ResponseVilleDTO saveVille(CreateVilleDTO villeDTO) ;

     Map<String,Object> getVilles(int page, int size);

    ResponseVilleDTO updateVille(CreateVilleDTO villeDTO, Long id) ;

    ResponseVilleDTO getVilleDTO(Long id) ;

    ResponseVilleDTO getVilleByName(String nom) ;

    void deleteVille(Long id) ;

    ResponseVilleDTO getVilleByCode(String code) ;
}
