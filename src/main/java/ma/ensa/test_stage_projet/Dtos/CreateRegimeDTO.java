package ma.ensa.test_stage_projet.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateRegimeDTO(
        @NotBlank(message = "champ obligatoire")
        @Size(min = 2, max = 100 , message = "chmap entre 2 et 100 lettres")
        String designation ,
        @NotBlank(message = "champ obligatoire")
        @Size(min = 1, max = 4 , message = "chmap entre 1 et 4 chiffres")
        String code
) {
}
