package ma.ensa.test_stage_projet.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateServiceExterieurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseServiceExterieurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseVilleDTO;
import ma.ensa.test_stage_projet.exceptions.AddresseAlreadyADD;
import ma.ensa.test_stage_projet.exceptions.NotFoundSEException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;
import ma.ensa.test_stage_projet.exceptions.VilleNotInSEException;
import ma.ensa.test_stage_projet.services.ServiceExterieurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/service-exterieurs")
public class ServiceExterieurController {
    private final ServiceExterieurService serviceExterieurService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllServiceExterieur() {
        List<Map<String,Object>> response = serviceExterieurService.getServiceExterieurDTOs();
        return ResponseEntity.ok(response);

    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getServiceExterieur(@PathVariable("id") Long id) {
        try{
           Map<String,Object> map=
                    serviceExterieurService.getServiceExterieurDTO(id);
           return ResponseEntity.ok(map);
        } catch (NotFoundSEException e) {
            Map<String,Object> response = new HashMap<>();
            response.put("message","error");
            response.put("error",e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    @GetMapping()
    public ResponseEntity<?> getServiceExterieurBy(
            @RequestParam(required = false) String nomSE,
            @RequestParam(required = false) String codeSE
    )  {
        long nbParams = Stream.of(nomSE,codeSE).filter(Objects::nonNull).count();

        if(nbParams !=1){
            return ResponseEntity.badRequest().body("vous devez specifier un seul parametre");
        }

        try {
            Map<String,Object> map = switch ((int) nbParams){
                case 1 -> {
                    if(nomSE != null) yield  serviceExterieurService.getServiceExterieurByName(nomSE);
                    yield  serviceExterieurService.getServiceExterieurDTOByCode(codeSE);
                }
                default -> throw new IllegalStateException();
            };

            return ResponseEntity.ok(map);
        }catch (NotFoundSEException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @PostMapping()
    public ResponseEntity<Map<String,Object>> createServiceExterieur(@RequestBody @Valid CreateServiceExterieurDTO serviceExterieurDTO){
        Map<String,Object> map = serviceExterieurService.saveExterieur(serviceExterieurDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(map);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteServiceExterieur(@PathVariable Long id){
        try{
            serviceExterieurService.deleteServiceExterieur(id);
            return ResponseEntity.noContent().build();

        }catch (NotFoundSEException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/{idSE}/villes")
    public ResponseEntity<?> addVilleToSE(@PathVariable Long idSE ,@RequestBody Long idVille){
        try{
            ResponseVilleDTO ville = serviceExterieurService.addVilleToSE(idSE , idVille);
            Map<String ,Object> map = new HashMap<>();
            map.put("message","Ville ajoute avec succées");
            map.put("ville",ville);
            return ResponseEntity.status(HttpStatus.CREATED).body(map);

        }catch (NotFoundSEException | NotFoundVilleException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{idSE}/villes/{idVille}")
    public ResponseEntity<?> removeVilleFromSE(@PathVariable Long idSE ,@PathVariable Long idVille){
        try{
            serviceExterieurService.deleteVilleFromSE(idSE , idVille);
            return ResponseEntity.noContent().build();

        }catch (NotFoundSEException | NotFoundVilleException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{idSE}")
    public ResponseEntity<?> updateSE(@PathVariable Long idSE,@RequestBody @Valid CreateServiceExterieurDTO serviceExterieurDTO){
        try{
            ResponseServiceExterieurDTO serviceExterieurDTO1 = serviceExterieurService.updateSE(serviceExterieurDTO,idSE);
            Map<String,Object> map = new HashMap<>();
            map.put("message","Service Exterieur update");
            map.put("serviceExterieurDTO",serviceExterieurDTO1);
            return ResponseEntity.ok(map);

        }catch (NotFoundSEException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/{idSE}/addresse")
    public ResponseEntity<?> addAddresse(@PathVariable Long idSE , @RequestBody Long idVille){
        try {
            Map<String,Object> map = serviceExterieurService.addAdresse(idSE , idVille);
            return ResponseEntity.status(HttpStatus.CREATED).body(map);

        }catch (NotFoundSEException | NotFoundVilleException | AddresseAlreadyADD e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{idSE}/addresse")
    public ResponseEntity<?> updateAddresse(@PathVariable Long idSE ,@RequestBody Long idNewVille){
        try {
            Map<String, Object> map = serviceExterieurService.updateAddresse(idSE , idNewVille);
            return ResponseEntity.ok(map);

        }catch (NotFoundSEException | NotFoundVilleException | VilleNotInSEException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

//    @DeleteMapping("/{idSE}/villes/{idVille}")
//
//    public ResponseEntity<?> deleteVilleFromSE(@PathVariable Long idSE, @PathVariable Long idVille) {
//        try {
//            serviceExterieurService.deleteVilleFromSE(idSE,idVille);
//            return ResponseEntity.noContent().build();
//        }catch (NotFoundSEException | NotFoundVilleException e){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//
//        }
//
//
//    }
}
