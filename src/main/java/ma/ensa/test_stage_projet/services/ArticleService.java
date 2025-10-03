package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreateArticleDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseArticleDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundArticleException;
import ma.ensa.test_stage_projet.exceptions.NotFoundCategorieException;
import ma.ensa.test_stage_projet.exceptions.NotFoundRegimeException;

import java.util.List;

public interface ArticleService {
    ResponseArticleDTO addArticle(CreateArticleDTO createArticleDTO) ;
    ResponseArticleDTO updateArticle(Long id,CreateArticleDTO createArticleDTO) ;
    void deleteArticle(Long id) throws NotFoundArticleException;
    ResponseArticleDTO getArticle(Long id) ;
    ResponseArticleDTO getArticleByDesignation(String designation) ;
    List<ResponseArticleDTO> getArticles();
}
