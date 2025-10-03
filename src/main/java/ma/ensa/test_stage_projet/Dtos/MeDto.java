package ma.ensa.test_stage_projet.Dtos;

import java.util.List;

public record MeDto(
    String email ,
    Long operateurId ,
    List<String> roles
) {
}
