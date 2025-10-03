package ma.ensa.test_stage_projet.web;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateProfileDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseProfileDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundProfileException;
import ma.ensa.test_stage_projet.services.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<?> addProfile(CreateProfileDTO createProfileDTO) {
        Map<String,Object> response = new HashMap<>();
        ResponseProfileDTO profileDTO = profileService.addProfile(createProfileDTO);
        response.put("message","profile added");
        response.put("profile",profileDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, CreateProfileDTO createProfileDTO) {
        Map<String,Object> response = new HashMap<>();
            ResponseProfileDTO profileDTO = profileService.updateProfile(id,createProfileDTO);
            response.put("message","profile updated");
            response.put("profile",profileDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long id) {
        Map<String,Object> response = new HashMap<>();
            profileService.deleteProfile(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfile(@PathVariable Long id) {
        Map<String,Object> response = new HashMap<>();
            ResponseProfileDTO profileDTO = profileService.getProfile(id);
            response.put("profile",profileDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllProfiles() {
        Map<String,Object> response = new HashMap<>();
        List<ResponseProfileDTO> responseProfileDTOList = profileService.getProfiles();
        response.put("profiles",responseProfileDTOList);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<?> getProfileByNom(@RequestParam String nom) {
        Map<String,Object> response = new HashMap<>();
            ResponseProfileDTO profileDTO = profileService.getProfileByNom(nom);
            response.put("profile",profileDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
