package ma.ensa.test_stage_projet.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateUtilisateurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseUtilisateurDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundOperateurException;
import ma.ensa.test_stage_projet.exceptions.NotFoundProfileException;
import ma.ensa.test_stage_projet.exceptions.NotFoundUtilisateur;
import ma.ensa.test_stage_projet.exceptions.NotUtilisatuerException;
import ma.ensa.test_stage_projet.services.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/utilisateurs")
@RequiredArgsConstructor
public class UtilisateurController
{
    private final UtilisateurService utilisateurService;
    @PostMapping
    public ResponseEntity<?> createUtilisateur(@RequestBody @Valid CreateUtilisateurDTO createUtilisateurDTO) {
        Map<String,Object> map = new HashMap<>();
            ResponseUtilisateurDTO responseUtilisateurDTO = utilisateurService.addUtilisateur(createUtilisateurDTO);
            map.put("message", "new utilisateur created");
            map.put("utilisateur", responseUtilisateurDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(map);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUtilisateur(@PathVariable Long id) {
        Map<String,Object> map = new HashMap<>();
            utilisateurService.deleteUtilisateur(id);
            //map.put("message", "utilisateur deleted");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUtilisateurById(@PathVariable Long id) {
        Map<String,Object> map = new HashMap<>();

            ResponseUtilisateurDTO responseUtilisateurDTO = utilisateurService.getUtilisateur(id);
            map.put("utilisateur", responseUtilisateurDTO);
            return ResponseEntity.status(HttpStatus.OK).body(map);

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUtilisateurs(@RequestParam(required = false) int page, @RequestParam(required = false) int size) {
        Map<String,Object> map = new HashMap<>();
        List<ResponseUtilisateurDTO> responseUtilisateurDTOS = utilisateurService.getUtilisateurs(page, size);
        map.put("utilisateurs", responseUtilisateurDTOS);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }

    @GetMapping
    public ResponseEntity<?> getAllUtilisateursByEmail(@RequestParam String email) {
        Map<String,Object> map = new HashMap<>();
            ResponseUtilisateurDTO responseUtilisateurDTO = utilisateurService.getUtilisateurByEmail(email);
            map.put("utilisateurs", responseUtilisateurDTO);
            return ResponseEntity.status(HttpStatus.OK).body(map);

    }
}
