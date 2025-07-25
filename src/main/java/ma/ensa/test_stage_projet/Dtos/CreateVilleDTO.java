package ma.ensa.test_stage_projet.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateVilleDTO(
        @NotBlank(message = "la designation est obligatoire")
        @Size(min= 3 , max = 100 ,message = "la designation doit etre entre 3 et 100 lettres")
        String designation ,
        @NotBlank(message = "le code est obligatoire")
        @Size(min = 1,max = 4,message = "le code doit etre entre 1 et 4 chiffres")
        String code
//        @NotNull(message = "L’ID ne peut pas être nul")
//        Long serviceExterieurID ,
//        boolean estAdresse
) {
}
