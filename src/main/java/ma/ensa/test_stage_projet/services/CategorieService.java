package ma.ensa.test_stage_projet.services;

import ma.ensa.test_stage_projet.Dtos.CreateCategorieDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseCategorieDTO;
import ma.ensa.test_stage_projet.entities.Categorie;
import ma.ensa.test_stage_projet.exceptions.NotFoundCategorieException;

import java.util.List;


public interface CategorieService {
    ResponseCategorieDTO create(CreateCategorieDTO createCategorieDTO);
    ResponseCategorieDTO update(Long id , CreateCategorieDTO createCategorieDTO) throws NotFoundCategorieException;
    void delete(Long id) throws NotFoundCategorieException;
    ResponseCategorieDTO findById(Long id) throws NotFoundCategorieException;
    ResponseCategorieDTO findByDesignation(String designation) throws NotFoundCategorieException;
    List<ResponseCategorieDTO> findAll();
}
