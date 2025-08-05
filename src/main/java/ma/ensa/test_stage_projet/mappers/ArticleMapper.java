package ma.ensa.test_stage_projet.mappers;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateArticleDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseArticleDTO;
import ma.ensa.test_stage_projet.entities.ArticleImport;
import ma.ensa.test_stage_projet.entities.Categorie;
import ma.ensa.test_stage_projet.entities.RegimeImportation;
import ma.ensa.test_stage_projet.exceptions.NotFoundCategorieException;
import ma.ensa.test_stage_projet.exceptions.NotFoundRegimeException;
import ma.ensa.test_stage_projet.repositories.CategorieRepository;
import ma.ensa.test_stage_projet.repositories.OperateurRepository;
import ma.ensa.test_stage_projet.repositories.RegimeImportationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleMapper {
    private final RegimeImportationRepository regimeImportationRepository;
    private final CategorieRepository categorieRepository;


    public ArticleImport fromCreate(CreateArticleDTO createArticleDTO) throws NotFoundRegimeException, NotFoundCategorieException {
        RegimeImportation regimeImportation = regimeImportationRepository.findById(createArticleDTO.regimeId())
                .orElseThrow(() -> new NotFoundRegimeException("regime not found"));
        Categorie categorie = categorieRepository.findById(createArticleDTO.categorieId())
                .orElseThrow(() -> new NotFoundCategorieException("categorie not found"));
        ArticleImport articleImport = new ArticleImport();
        articleImport.setDesignationDc(createArticleDTO.designation());
        articleImport.setRegime_import(regimeImportation);
        articleImport.setCategorie(categorie);
        return articleImport;
    }

    public ResponseArticleDTO toResponse(ArticleImport articleImport){
        return new ResponseArticleDTO(
                articleImport.getId(),
                articleImport.getDesignationDc(),
                articleImport.getRegime_import().getDesignation(),
                articleImport.getCategorie().getDesignation()
        );
    }
}
