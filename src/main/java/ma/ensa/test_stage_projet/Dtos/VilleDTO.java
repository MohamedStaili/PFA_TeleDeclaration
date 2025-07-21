package ma.ensa.test_stage_projet.Dtos;

import lombok.Data;
import ma.ensa.test_stage_projet.entities.ServiceExterieur;
@Data
public class VilleDTO {
    private Long id;
    private String designation;
    private String code ;
    private Long id_se;
}
