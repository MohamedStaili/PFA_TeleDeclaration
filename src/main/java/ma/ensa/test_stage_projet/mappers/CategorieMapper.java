package ma.ensa.test_stage_projet.mappers;

import ma.ensa.test_stage_projet.Dtos.CreateCategorieDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseCategorieDTO;
import ma.ensa.test_stage_projet.entities.Categorie;
import org.springframework.stereotype.Service;

@Service
public class CategorieMapper {

    public Categorie fromCreate(CreateCategorieDTO createCategorieDTO){
        Categorie categorie = new Categorie();
        categorie.setDesignation(createCategorieDTO.designation());
        return categorie;
    }
    public ResponseCategorieDTO toResponse(Categorie categorie){
        return new ResponseCategorieDTO(
              categorie.getId(),
              categorie.getDesignation()
        );
    }

}
