package ma.ensa.test_stage_projet.Dtos;

import lombok.Data;

@Data
public class ServiceExterieurDTO {
    private Long id_se;
    private String nomSE;
    private Long ville_id;
}
