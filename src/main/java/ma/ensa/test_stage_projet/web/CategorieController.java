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

    @GetMapping()
    public ResponseEntity<?> getCategorie(@RequestParam(required = false) Long id
            , @RequestParam(required = false) String designation){

        long nbParams = Stream.of(id,designation).filter(Objects::nonNull).count();
        if(nbParams != 1) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nombre de parametres n'est pas autorise");

        try {
            ResponseCategorieDTO responseCategorieDTO = switch ((int) nbParams){
                case 1 -> {
                    if(id!=null) yield categorieService.findById(id);
                    yield categorieService.findByDesignation(designation);
                }
                default -> throw new IllegalStateException("Unexpected value: " + nbParams);
            };
            Map<String, Object> map = new HashMap<>();
            map.put("categorie", responseCategorieDTO);
            return ResponseEntity.status(HttpStatus.OK).body(map);

        }catch(NotFoundCategorieException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }
    }

}
