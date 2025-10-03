package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreateDeclarationDTO;
import ma.ensa.test_stage_projet.exceptions.*;
import ma.ensa.test_stage_projet.sec.UserDetailsInfo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface DeclarationService {
    void create(CreateDeclarationDTO dto , UserDetailsInfo user) ;

}
