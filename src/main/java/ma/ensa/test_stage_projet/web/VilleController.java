package ma.ensa.test_stage_projet.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateVilleDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseVilleDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundSEException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;
import ma.ensa.test_stage_projet.services.VilleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/villes")
@RequiredArgsConstructor
public class VilleController {
    private final VilleService villeService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllVilles(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "5")int size )
    {
        Map<String,Object> response = villeService.getVilles(page, size);

        return  ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getVille(@PathVariable Long id) {
        Map<String,Object> response = new HashMap<>();

            ResponseVilleDTO villeDTO = villeService.getVilleDTO(id);
            response.put("ville",villeDTO);
            return ResponseEntity.ok(response);
    }
    @GetMapping()
    public ResponseEntity<?> getVilleBy(@RequestParam(required = false) String nom,
                                          @RequestParam(required = false) String code) {
        long nbParams = Stream.of(nom,code).filter(Objects::nonNull).count();
        if(nbParams !=1){
            return ResponseEntity.badRequest().body("vous devez specifier un seul parametre");
        }
            ResponseVilleDTO villeDTO = switch ((int) nbParams){
                case 1 ->{
                    if (nom != null) yield villeService.getVilleByName(nom);
                    yield villeService.getVilleByCode(code);
                }
                default -> throw new IllegalStateException();
            };
            return ResponseEntity.ok(villeDTO);

        }


    @PostMapping()
    public ResponseEntity<?> createVille(@RequestBody @Valid CreateVilleDTO villeDTO) {
            ResponseVilleDTO saveDto = villeService.saveVille(villeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVille(@PathVariable Long id, @RequestBody @Valid CreateVilleDTO villeDTO) {
            ResponseVilleDTO saveDto = villeService.updateVille(villeDTO,id);
            return ResponseEntity.ok(saveDto);


    }

    @DeleteMapping("/{id}")

    public ResponseEntity<?> deleteVille(@PathVariable Long id) {
            villeService.deleteVille(id);
            return ResponseEntity.noContent().build();

    }
}
