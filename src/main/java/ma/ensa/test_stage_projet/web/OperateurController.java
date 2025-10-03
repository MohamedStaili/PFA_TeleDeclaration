package ma.ensa.test_stage_projet.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateOperatuerDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseOperateurDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundOperateurException;
import ma.ensa.test_stage_projet.exceptions.NotFoundProfileException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;
import ma.ensa.test_stage_projet.services.OperateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/operateurs")
@RequiredArgsConstructor
public class OperateurController {
    private final OperateurService operateurService;

    @PostMapping
    public ResponseEntity<?> addOperateur(@RequestBody @Valid CreateOperatuerDTO createOperatuerDTO){
        Map<String,Object> response = new HashMap<>();
            ResponseOperateurDTO responseOperateurDTO = operateurService.addOperateur(createOperatuerDTO);
            response.put("message","operateur added");
            response.put("operateur", responseOperateurDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateOperateur(@PathVariable Long id ,@RequestBody @Valid CreateOperatuerDTO createOperatuerDTO){
        Map<String,Object> response = new HashMap<>();
            ResponseOperateurDTO responseOperateurDTO = operateurService.updateOperateur(id, createOperatuerDTO);
            response.put("message","operateur updated");
            response.put("operateur", responseOperateurDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOperateur(@PathVariable Long id){
        Map<String,Object> response = new HashMap<>();
            operateurService.deleteOperateur(id);
            response.put("message","operateur deleted");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getOperateur(@PathVariable Long id){
        Map<String,Object> response = new HashMap<>();
            ResponseOperateurDTO responseOperateurDTO = operateurService.getOperateur(id);
            response.put("message","operateur found");
            response.put("operateur", responseOperateurDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getOperateurs(){
        Map<String,Object> response = new HashMap<>();
        List<ResponseOperateurDTO> responseOperateurDTOS = operateurService.getOperateurs();
        response.put("operateurs", responseOperateurDTOS);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping
    public ResponseEntity<?> getOperateurByCodeCp(@RequestParam Long code){
        Map<String,Object> response = new HashMap<>();
            ResponseOperateurDTO responseOperateurDTO =operateurService.getOperateurByCode(code);
            response.put("message","operateur found");
            response.put("operateur", responseOperateurDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
