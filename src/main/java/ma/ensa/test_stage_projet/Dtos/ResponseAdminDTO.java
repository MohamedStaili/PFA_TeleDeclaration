package ma.ensa.test_stage_projet.Dtos;

public record ResponseAdminDTO(
        Long id ,
        String email ,
        boolean active ,
        String profile
) {
}
