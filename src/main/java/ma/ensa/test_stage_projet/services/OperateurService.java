package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreateOperatuerDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseOperateurDTO;
import ma.ensa.test_stage_projet.entities.Operateur;
import ma.ensa.test_stage_projet.exceptions.NotFoundOperateurException;
import ma.ensa.test_stage_projet.exceptions.NotFoundProfileException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;

import java.util.List;

public interface OperateurService {
    ResponseOperateurDTO addOperateur(CreateOperatuerDTO createOperatuerDTO) throws NotFoundVilleException, NotFoundProfileException;
    ResponseOperateurDTO updateOperateur(Long id ,CreateOperatuerDTO createOperatuerDTO) throws NotFoundOperateurException, NotFoundVilleException, NotFoundProfileException;
    void deleteOperateur(Long id) throws NotFoundOperateurException;
    ResponseOperateurDTO getOperateur(Long id) throws NotFoundOperateurException;
    ResponseOperateurDTO getOperateurByCode(Long code) throws NotFoundOperateurException;
    List<ResponseOperateurDTO> getOperateurs();
}
