package ma.ensa.test_stage_projet.web;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.VilleDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;
import ma.ensa.test_stage_projet.services.VilleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/service-exterieurs/")
@RequiredArgsConstructor
public class VilleController {
    private final VilleService villeService;

    @GetMapping
    public List<VilleDTO> getAllVilles() throws NotFoundVilleException {

    }
}
