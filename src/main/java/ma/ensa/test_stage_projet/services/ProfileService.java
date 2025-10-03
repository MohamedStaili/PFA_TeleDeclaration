package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreateProfileDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseProfileDTO;
import ma.ensa.test_stage_projet.exceptions.DuplicateNomException;
import ma.ensa.test_stage_projet.exceptions.NotFoundProfileException;

import java.util.List;

public interface ProfileService {
    ResponseProfileDTO addProfile(CreateProfileDTO createProfileDTO);
    ResponseProfileDTO updateProfile(Long id , CreateProfileDTO createProfileDTO) ;
    void deleteProfile(Long id) ;
    ResponseProfileDTO getProfile(Long id) ;
    ResponseProfileDTO getProfileByNom(String nom) ;
    List<ResponseProfileDTO> getProfiles();
}
