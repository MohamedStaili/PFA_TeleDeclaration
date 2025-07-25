package ma.ensa.test_stage_projet.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreatePortDTO;
import ma.ensa.test_stage_projet.Dtos.ResponsePortDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundPortException;
import ma.ensa.test_stage_projet.exceptions.NotFoundSEException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;
import ma.ensa.test_stage_projet.services.PortService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ports")
public class PortController {
    private final PortService portService;

    @PostMapping()
    public ResponseEntity<?> addPort(@RequestBody @Valid CreatePortDTO createPortDTO){
        try {
            ResponsePortDTO responsePortDTO = portService.addPort(createPortDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(responsePortDTO);
        }catch (NotFoundVilleException | NotFoundSEException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePort(@PathVariable Long id , @RequestBody @Valid CreatePortDTO createPortDTO) {
        try {
            ResponsePortDTO responsePortDTO = portService.updatePort(createPortDTO , id);
            return ResponseEntity.ok(responsePortDTO);
        } catch (NotFoundPortException | NotFoundSEException | NotFoundVilleException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePort(@PathVariable Long id) {
        try {
            portService.deletePort(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (NotFoundPortException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPort(@PathVariable Long id) {
        try {
            ResponsePortDTO responsePortDTO = portService.getPort(id);
            return ResponseEntity.ok(responsePortDTO);
        } catch (NotFoundPortException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping()
    public ResponseEntity<?> getAllPorts() {
        List<ResponsePortDTO> responsePortDTOS = portService.getPorts();
        return ResponseEntity.ok(responsePortDTOS);
    }
}
