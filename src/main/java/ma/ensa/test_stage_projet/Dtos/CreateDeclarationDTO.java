package ma.ensa.test_stage_projet.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record CreateDeclarationDTO(
        @NotNull(message = "required")
        Long regime ,
        Date dateLimitArr ,
        @NotNull(message = "required")
        Long port1 ,
        Long port2 ,
        @NotNull(message = "required")
        Long article ,
        @NotNull(message = "required")
        BigDecimal qte ,
        @NotNull(message = "required")
        Integer compteTiers

) {
}
