package ma.ensa.test_stage_projet.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record LoginDto(
        @Email(message = "entrer un email valide")
        String email ,
//        @Pattern(
//                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
//                message = "Password must be at least 8 characters long, contain one uppercase, one lowercase, one number, and one special character"
//        )
        String password
) {
}
