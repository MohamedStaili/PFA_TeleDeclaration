package ma.ensa.test_stage_projet.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VilleDTO(
        Long id ,
        @NotBlank(message = "la designation est obligatoire")
        @Size(min= 3 , max = 100 ,message = "la designation doit etre entre 3 et 100 lettres")
        String designation ,
        @NotBlank(message = "le code est obligatoire")
        @Size(min = 4,max = 4,message = "le code doit etre 4 chiffres")
        String code ,
        Long idSE
) {
}
