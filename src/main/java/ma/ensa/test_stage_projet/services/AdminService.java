package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreateAdminDTO;
import ma.ensa.test_stage_projet.Dtos.CreateUtilisateurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseAdminDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseUtilisateurDTO;
import ma.ensa.test_stage_projet.exceptions.NotAdminException;
import ma.ensa.test_stage_projet.exceptions.NotFoundOperateurException;
import ma.ensa.test_stage_projet.exceptions.NotFoundProfileException;
import ma.ensa.test_stage_projet.exceptions.NotFoundUtilisateur;

import java.util.List;

public interface AdminService {
    ResponseAdminDTO addAdmin(CreateAdminDTO createAdminDTO) throws NotFoundProfileException;

    ResponseAdminDTO updateAdmin(Long id, CreateAdminDTO createAdminDTO);

    void deleteAdmin(Long id) throws NotFoundUtilisateur, NotAdminException;

    ResponseAdminDTO getAdmin(Long id) throws NotFoundUtilisateur;

    List<ResponseAdminDTO> getAdmins(int page , int size);

    ResponseAdminDTO getAdminByEmail(String email) throws NotFoundUtilisateur;
}
