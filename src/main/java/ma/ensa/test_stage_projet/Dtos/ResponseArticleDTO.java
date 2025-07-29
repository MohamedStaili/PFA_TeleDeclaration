package ma.ensa.test_stage_projet.Dtos;

public record ResponseArticleDTO(
        Long id ,
        String designation ,
        String regimeId ,
        String categorieId
) {
}
