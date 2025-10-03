package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreateOperatuerDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseOperateurDTO;
import ma.ensa.test_stage_projet.entities.Operateur;
import ma.ensa.test_stage_projet.exceptions.NotFoundOperateurException;
import ma.ensa.test_stage_projet.exceptions.NotFoundProfileException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;

import java.util.List;

public interface OperateurService {
    ResponseOperateurDTO addOperateur(CreateOperatuerDTO createOperatuerDTO) ;
    ResponseOperateurDTO updateOperateur(Long id ,CreateOperatuerDTO createOperatuerDTO) ;
    void deleteOperateur(Long id) ;
    ResponseOperateurDTO getOperateur(Long id) ;
    ResponseOperateurDTO getOperateurByCode(Long code) ;
    List<ResponseOperateurDTO> getOperateurs();
}
