package ma.ensa.test_stage_projet.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.ActivateRequestDTO;
import ma.ensa.test_stage_projet.Dtos.MeDto;
import ma.ensa.test_stage_projet.exceptions.NotFoundUtilisateur;
import ma.ensa.test_stage_projet.exceptions.TokenExperedException;
import ma.ensa.test_stage_projet.sec.UserDetailsInfo;
import ma.ensa.test_stage_projet.services.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
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
            accountService.activateUtilisateur(token,request.password());
            response.put("message", "Activation done");
            return ResponseEntity.ok(response);
    }
    @GetMapping("/me")
    public ResponseEntity<?> me(@AuthenticationPrincipal UserDetailsInfo user) {
        MeDto dto = new MeDto(
                user.getUsername(),
                user.getOperateur() ,
                user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList()) ;

        return ResponseEntity.ok().body(dto);
    }
}
