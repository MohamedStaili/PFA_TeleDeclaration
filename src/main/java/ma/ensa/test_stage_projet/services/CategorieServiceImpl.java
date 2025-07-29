package ma.ensa.test_stage_projet.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateCategorieDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseCategorieDTO;
import ma.ensa.test_stage_projet.entities.Categorie;
import ma.ensa.test_stage_projet.exceptions.NotFoundCategorieException;
import ma.ensa.test_stage_projet.mappers.CategorieMapper;
import ma.ensa.test_stage_projet.repositories.CategorieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategorieServiceImpl implements CategorieService {
    private final CategorieMapper categorieMapper;
    private final CategorieRepository categorieRepository;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseCategorieDTO create(CreateCategorieDTO createCategorieDTO) {
        Categorie categorie = categorieMapper.fromCreate(createCategorieDTO);
        Categorie categorieSaved = categorieRepository.save(categorie);
        return categorieMapper.toResponse(categorieSaved);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseCategorieDTO update(Long id, CreateCategorieDTO createCategorieDTO) throws NotFoundCategorieException {
        Categorie categorie = categorieRepository.findById(id).orElseThrow(() -> new NotFoundCategorieException("not found categorie"));
        categorie.setDesignation(categorie.getDesignation());
        Categorie categorieSaved = categorieRepository.save(categorie);
        return categorieMapper.toResponse(categorieSaved);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Long id) throws NotFoundCategorieException {
        Categorie categorie = categorieRepository.findById(id).orElseThrow(() -> new NotFoundCategorieException("not found categorie"));
        categorieRepository.delete(categorie);
    }

    @Override
    public ResponseCategorieDTO findById(Long id) throws NotFoundCategorieException {
        Categorie categorie = categorieRepository.findById(id).orElseThrow(() -> new NotFoundCategorieException("not found categorie"));
        return categorieMapper.toResponse(categorie);
    }

    @Override
    public ResponseCategorieDTO findByDesignation(String designation) throws NotFoundCategorieException {
        Categorie categorie = categorieRepository.findByDesignation(designation);
        if (categorie == null) throw new  NotFoundCategorieException("not found categorie");
        return categorieMapper.toResponse(categorie);
    }

    @Override
    public List<ResponseCategorieDTO> findAll() {
        List<Categorie> categorieList = categorieRepository.findAll();
        return categorieList.stream().map(categorieMapper::toResponse).collect(Collectors.toList());
    }
}
