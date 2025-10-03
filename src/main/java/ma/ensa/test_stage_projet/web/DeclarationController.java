package ma.ensa.test_stage_projet.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateDeclarationDTO;
import ma.ensa.test_stage_projet.exceptions.*;
import ma.ensa.test_stage_projet.sec.UserDetailsInfo;
import ma.ensa.test_stage_projet.services.DeclarationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/declarerations")
@RequiredArgsConstructor
public class DeclarationController {
    private final DeclarationService declarationService;

    @PostMapping
    public ResponseEntity<?> addDeclaration(@RequestBody @Valid CreateDeclarationDTO dto , @AuthenticationPrincipal UserDetailsInfo user) {
            declarationService.create(dto , user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Declaration added");
    }
}
