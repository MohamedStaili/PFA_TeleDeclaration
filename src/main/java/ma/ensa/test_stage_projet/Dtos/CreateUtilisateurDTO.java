package ma.ensa.test_stage_projet.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUtilisateurDTO(
        @NotNull
        Long operateurId ,
        @NotBlank(message = "champ obligatoire")
        @Email(message = "Email must be valid")
        String email
) {
}
