package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreateUtilisateurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseUtilisateurDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundOperateurException;
import ma.ensa.test_stage_projet.exceptions.NotFoundProfileException;
import ma.ensa.test_stage_projet.exceptions.NotFoundUtilisateur;
import ma.ensa.test_stage_projet.exceptions.NotUtilisatuerException;

import java.util.List;

public interface UtilisateurService {
    ResponseUtilisateurDTO addUtilisateur(CreateUtilisateurDTO createUtilisateurDTO) throws NotFoundOperateurException, NotFoundProfileException;

    ResponseUtilisateurDTO updateUtilisateur(Long id, CreateUtilisateurDTO createUtilisateurDTO);

    void deleteUtilisateur(Long id) throws NotFoundUtilisateur, NotUtilisatuerException;

    ResponseUtilisateurDTO getUtilisateur(Long id) throws NotFoundUtilisateur;

    List<ResponseUtilisateurDTO> getUtilisateurs(int page , int size);

    ResponseUtilisateurDTO getUtilisateurByEmail(String email) throws NotFoundUtilisateur;
}
