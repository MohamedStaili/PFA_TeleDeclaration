package ma.ensa.test_stage_projet.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ActivateRequestDTO(
//        @NotBlank(message = "champ obligatoire")
//        String token,
        @NotBlank(message = "champ obligatoire")
        @Size(min = 8, max = 100 , message = "entre 8 et 100 characteres")
        String password

) {
}
