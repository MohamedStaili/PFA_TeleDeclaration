package ma.ensa.test_stage_projet.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateRegimeDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseRegimeDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundRegimeException;
import ma.ensa.test_stage_projet.services.RegimeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/regime-importations")
@RequiredArgsConstructor
public class RegimeController {
    private final RegimeService regimeService;

    @PostMapping()
    public ResponseEntity<?> addRegime(@RequestBody @Valid CreateRegimeDTO createRegimeDTO)  {
        ResponseRegimeDTO responseRegimeDTO = regimeService.addRegimeImportation(createRegimeDTO);
        Map<String,Object> response = new HashMap<>();
        response.put("message","Regime added");
        response.put("response", responseRegimeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> addRegime(@RequestBody @Valid CreateRegimeDTO createRegimeDTO,@PathVariable Long id)  {
        try{
            ResponseRegimeDTO responseRegimeDTO = regimeService.updateRegimeImportation(createRegimeDTO , id);
            Map<String,Object> response = new HashMap<>();
            response.put("message","Regime updated");
            response.put("response", responseRegimeDTO);
            return ResponseEntity.ok(response);
        }catch (NotFoundRegimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteRegime(@PathVariable Long id)  {
        try{
            regimeService.deleteRegimeImportation(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Regime deleted");

        }catch (NotFoundRegimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping()
    public ResponseEntity<?> getAllRegimes() {
        List<ResponseRegimeDTO> responseRegimeDTOS = regimeService.findAllRegimes();
        Map<String,Object> response = new HashMap<>();
        response.put("regimes_importations",responseRegimeDTOS);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping()
    public ResponseEntity<?> getRegime(@RequestParam Long id,@RequestParam String designation , @RequestParam String code)  {
        long nbParams = Stream.of(id,designation,code).filter(Objects::nonNull).count();
        if(nbParams==null) throw new

    }
}
