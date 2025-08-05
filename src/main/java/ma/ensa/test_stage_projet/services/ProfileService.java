package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreateProfileDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseProfileDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundProfileException;

import java.util.List;

public interface ProfileService {
    ResponseProfileDTO addProfile(CreateProfileDTO createProfileDTO);
    ResponseProfileDTO updateProfile(Long id , CreateProfileDTO createProfileDTO) throws NotFoundProfileException;
    void deleteProfile(Long id) throws NotFoundProfileException;
    ResponseProfileDTO getProfile(Long id) throws NotFoundProfileException;
    ResponseProfileDTO getProfileByNom(String nom) throws NotFoundProfileException;
    List<ResponseProfileDTO> getProfiles();
}
