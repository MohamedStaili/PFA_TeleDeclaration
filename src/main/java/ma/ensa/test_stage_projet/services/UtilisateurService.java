package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreateUtilisateurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseUtilisateurDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundOperateurException;
import ma.ensa.test_stage_projet.exceptions.NotFoundProfileException;

import java.util.List;

public interface UtilisateurService {
    ResponseUtilisateurDTO addUtilisateur(CreateUtilisateurDTO createUtilisateurDTO) throws NotFoundOperateurException, NotFoundProfileException;
    void activateUtilisateur(String Password);
    ResponseUtilisateurDTO updateUtilisateur(Long id , CreateUtilisateurDTO createUtilisateurDTO);
    void deleteUtilisateur(Long id);
    ResponseUtilisateurDTO getUtilisateur(Long id);
    List<ResponseUtilisateurDTO> getUtilisateurs();
    ResponseUtilisateurDTO getUtilisateurByEmail(String email);
    ResponseUtilisateurDTO getUtilisateurByActivationToken(String activationToken);
}
