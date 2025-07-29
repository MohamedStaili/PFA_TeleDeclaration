package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreateArticleDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseArticleDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundArticleException;
import ma.ensa.test_stage_projet.exceptions.NotFoundCategorieException;
import ma.ensa.test_stage_projet.exceptions.NotFoundRegimeException;

import java.util.List;

public interface ArticleService {
    ResponseArticleDTO addArticle(CreateArticleDTO createArticleDTO) throws NotFoundRegimeException, NotFoundCategorieException;
    ResponseArticleDTO updateArticle(Long id,CreateArticleDTO createArticleDTO) throws NotFoundArticleException, NotFoundRegimeException, NotFoundCategorieException;
    void deleteArticle(Long id) throws NotFoundArticleException;
    ResponseArticleDTO getArticle(Long id) throws NotFoundArticleException;
    ResponseArticleDTO getArticleByDesignation(String designation) throws NotFoundArticleException;
    List<ResponseArticleDTO> getArticles();
}
