package ma.ensa.test_stage_projet.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateOperatuerDTO(
        @NotBlank(message = "champ obligatoire")
        @Size(min = 5, max = 30 , message = "entre 5 et 30")
        String raisonSoc ,
        @NotNull(message = "champ obligatoire")
        Long profil ,
        @NotBlank(message = "champ obligatoire")
        @Size(min = 5, max = 30 , message = "entre 5 et 30")
        String numPatente ,
        @NotBlank(message = "champ obligatoire")
        @Size(min = 5, max = 30 , message = "entre 5 et 30")
        String registreCom ,
        @NotBlank(message = "champ obligatoire")
        @Size(min = 5, max = 100 , message = "entre 5 et 100")
        String addresse ,
        @NotBlank(message = "champ obligatoire")
        @Size(min = 5, max = 30 , message = "entre 5 et 30")
        String ice ,
        @NotNull(message = "champ obligatoire")
        Long villeID
) {
}
