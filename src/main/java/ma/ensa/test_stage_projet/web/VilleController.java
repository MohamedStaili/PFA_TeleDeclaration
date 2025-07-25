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

    @GetMapping()
    public ResponseEntity<?> getVille(@RequestParam(required = false) Long id,
                                          @RequestParam(required = false) String nom,
                                          @RequestParam(required = false) String code) {
        long nbParams = Stream.of(id,nom,code).filter(Objects::nonNull).count();
        if(nbParams !=1){
            return ResponseEntity.badRequest().body("vous devez specifier un seul parametre");
        }
        try{
            ResponseVilleDTO villeDTO = switch ((int) nbParams){
                case 1 ->{
                    if (id != null) yield villeService.getVilleDTO(id);
                    if (nom != null) yield villeService.getVilleByName(nom);
                    yield villeService.getVilleByCode(code);
                }
                default -> throw new IllegalStateException();
            };
            return ResponseEntity.ok(villeDTO);
            } catch (NotFoundVilleException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> createVille(@RequestBody @Valid CreateVilleDTO villeDTO) {
            ResponseVilleDTO saveDto = villeService.saveVille(villeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(saveDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVille(@PathVariable Long id, @RequestBody @Valid CreateVilleDTO villeDTO) {
        try{
            ResponseVilleDTO saveDto = villeService.updateVille(villeDTO,id);
            return ResponseEntity.ok(saveDto);
        }catch (NotFoundSEException | NotFoundVilleException e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")

    public ResponseEntity<?> deleteVille(@PathVariable Long id) {
        try{
            villeService.deleteVille(id);
            return ResponseEntity.noContent().build();
        }catch (NotFoundVilleException e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
