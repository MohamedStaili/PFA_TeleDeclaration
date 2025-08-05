package ma.ensa.test_stage_projet.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateProfileDTO(
        @NotBlank(message = "champ obligatoire")
        @Size(min = 3 , max = 30 ,message = "entre 3 et 30 lettres")
        String nom ,
        @NotBlank(message = "champ obligatoire")
        @Size(min = 3 , max = 100 ,message = "entre 3 et 30 lettres")
        String description

) {
}
