package ma.ensa.test_stage_projet.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateProfileDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseProfileDTO;
import ma.ensa.test_stage_projet.entities.Profile;
import ma.ensa.test_stage_projet.exceptions.NotFoundProfileException;
import ma.ensa.test_stage_projet.mappers.ProfileMapper;
import ma.ensa.test_stage_projet.repositories.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseProfileDTO addProfile(CreateProfileDTO createProfileDTO) {
        Profile profile = profileMapper.fromCreate(createProfileDTO);
        Profile savedProfile = profileRepository.save(profile);
        return profileMapper.toResponse(savedProfile);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseProfileDTO updateProfile(Long id , CreateProfileDTO createProfileDTO) throws NotFoundProfileException {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundProfileException("not found Profile"));
        profile.setNom(profile.getNom());
        profile.setDescription(profile.getDescription());
        Profile savedProfile = profileRepository.save(profile);
        return profileMapper.toResponse(savedProfile);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteProfile(Long id) throws NotFoundProfileException {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundProfileException("not found Profile"));
        profileRepository.delete(profile);
    }

    @Override
    public ResponseProfileDTO getProfile(Long id) throws NotFoundProfileException {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new NotFoundProfileException("not found Profile"));
        return profileMapper.toResponse(profile);
    }

    @Override
    public ResponseProfileDTO getProfileByNom(String nom) throws NotFoundProfileException {
        Profile profile = profileRepository.findByNom(nom);
        if (profile == null) throw new NotFoundProfileException("not found Profile");
        return profileMapper.toResponse(profile);
    }

    @Override
    public List<ResponseProfileDTO> getProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream().map(profileMapper::toResponse).collect(Collectors.toList());
    }
}
