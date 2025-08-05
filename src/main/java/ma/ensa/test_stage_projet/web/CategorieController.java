package ma.ensa.test_stage_projet.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateCategorieDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseCategorieDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundCategorieException;
import ma.ensa.test_stage_projet.services.CategorieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategorieController {
    private final CategorieService categorieService;

    @PostMapping()
    public ResponseEntity<?> addCategorie(@Valid @RequestBody CreateCategorieDTO createCategorieDTO){
        ResponseCategorieDTO responseCategorieDTO = categorieService.create(createCategorieDTO);
        Map<String, Object> map = new HashMap<>();
        map.put("message","categorie added");
        map.put("categorie", responseCategorieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CreateCategorieDTO createCategorieDTO){
        try {
            ResponseCategorieDTO responseCategorieDTO = categorieService.update(id,createCategorieDTO);
            Map<String, Object> map = new HashMap<>();
            map.put("message","categorie updated");
            map.put("categorie", responseCategorieDTO);
            return ResponseEntity.status(HttpStatus.OK).body(map);

        }catch (NotFoundCategorieException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            categorieService.delete(id);
            Map<String, Object> map = new HashMap<>();
            map.put("message","categorie deleted");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
        }catch (NotFoundCategorieException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        List<ResponseCategorieDTO> responseCategorieDTOS = categorieService.findAll();
        Map<String, Object> map = new HashMap<>();
        map.put("categorie", responseCategorieDTOS);
        return ResponseEntity.status(HttpStatus.OK).body(map);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategorie(@PathVariable Long id){
        Map<String,Object> response = new HashMap<>();
        try{
            ResponseCategorieDTO responseCategorieDTO = categorieService.findById(id);
            response.put("categorie",responseCategorieDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (NotFoundCategorieException e) {
            response.put("message","error");
            response.put("error",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    @GetMapping()
    public ResponseEntity<?> getCategorieByDesignation(@RequestParam String designation){
        Map<String,Object> response = new HashMap<>();
        try {
            ResponseCategorieDTO responseCategorieDTO = categorieService.findByDesignation(designation);
            response.put("categorie",responseCategorieDTO);
            return ResponseEntity.status(HttpStatus.OK).body(response);

        }catch(NotFoundCategorieException e){
            response.put("message","error");
            response.put("error",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

        }
    }

}
