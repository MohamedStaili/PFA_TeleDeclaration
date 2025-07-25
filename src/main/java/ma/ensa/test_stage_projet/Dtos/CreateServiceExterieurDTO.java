package ma.ensa.test_stage_projet.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

;

public record CreateServiceExterieurDTO(

        @NotBlank(message = "le nom est obligatoire")
        @Size(min= 3 , max = 100 ,message = "la nom doit etre entre 3 et 100 lettres")
        String nomSE,
        @NotBlank(message = "le code est obligatoire")
        @Size(min = 1,max = 4,message = "le code doit etre 4 chiffres")
        String code

) {

}
