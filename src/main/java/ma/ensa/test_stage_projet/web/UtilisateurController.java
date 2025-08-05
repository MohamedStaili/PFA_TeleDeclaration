package ma.ensa.test_stage_projet.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateUtilisateurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseUtilisateurDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundOperateurException;
import ma.ensa.test_stage_projet.exceptions.NotFoundProfileException;
import ma.ensa.test_stage_projet.services.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
        try{
            ResponseUtilisateurDTO responseUtilisateurDTO = utilisateurService.addUtilisateur(createUtilisateurDTO);
            map.put("message", "new utilisateur created");
            map.put("utilisateur", responseUtilisateurDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(map);
        }catch (NotFoundOperateurException | NotFoundProfileException e){
            map.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }


    }
}
