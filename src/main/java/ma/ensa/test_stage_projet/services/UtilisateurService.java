package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreateUtilisateurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseUtilisateurDTO;
import ma.ensa.test_stage_projet.exceptions.*;

import java.util.List;

public interface UtilisateurService {
    ResponseUtilisateurDTO addUtilisateur(CreateUtilisateurDTO createUtilisateurDTO);

    ResponseUtilisateurDTO updateUtilisateur(Long id, CreateUtilisateurDTO createUtilisateurDTO);

    void deleteUtilisateur(Long id) ;

    ResponseUtilisateurDTO getUtilisateur(Long id) ;

    List<ResponseUtilisateurDTO> getUtilisateurs(int page , int size);

    ResponseUtilisateurDTO getUtilisateurByEmail(String email) ;
}
