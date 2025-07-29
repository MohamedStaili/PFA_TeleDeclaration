package ma.ensa.test_stage_projet.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateArticleDTO(
        @NotBlank(message = "champ obligatoire")
        @Size(min = 2 ,max = 30 , message = "entre 2 et 30 lettres")
        String designation ,
        @NotNull
        Long regimeId ,
        @NotNull
        Long categorieId
) {
}
