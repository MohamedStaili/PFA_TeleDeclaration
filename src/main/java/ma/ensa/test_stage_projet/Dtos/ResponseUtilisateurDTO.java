package ma.ensa.test_stage_projet.Dtos;

public record ResponseUtilisateurDTO(
        Long id ,
        String email ,
        boolean active ,
        Long operateur ,
        String profile
) {
}
