package ma.ensa.test_stage_projet.Dtos;

public record ResponseOperateurDTO(
        Long id ,
        String code ,
        String raisonSoc ,
        Long codeCptable ,
        Long actif ,
        Long profil ,
        String numPatente ,
        String registreCom ,
        String adresse ,
        String ice ,
        String ville
) {
}
