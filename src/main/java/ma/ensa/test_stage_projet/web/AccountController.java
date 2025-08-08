package ma.ensa.test_stage_projet.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.ActivateRequestDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundUtilisateur;
import ma.ensa.test_stage_projet.exceptions.TokenExperedException;
import ma.ensa.test_stage_projet.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @PostMapping("/activate")
    public ResponseEntity<?> activateAccount(@RequestParam String token, @RequestBody @Valid ActivateRequestDTO request) {
        Map<String,Object> response = new HashMap<>();
        try {
            accountService.activateUtilisateur(token,request.password());
            response.put("message", "Activation done");
            return ResponseEntity.ok(response);
        } catch (NotFoundUtilisateur | TokenExperedException e) {
           response.put("message", e.getMessage());
           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
