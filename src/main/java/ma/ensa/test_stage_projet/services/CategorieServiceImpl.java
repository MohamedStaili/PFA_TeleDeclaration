package ma.ensa.test_stage_projet.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateCategorieDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseCategorieDTO;
import ma.ensa.test_stage_projet.entities.Categorie;
import ma.ensa.test_stage_projet.exceptions.DuplicateDesignationException;
import ma.ensa.test_stage_projet.exceptions.NotFoundCategorieException;
import ma.ensa.test_stage_projet.mappers.CategorieMapper;
import ma.ensa.test_stage_projet.repositories.CategorieRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategorieServiceImpl implements CategorieService {
    private final CategorieMapper categorieMapper;
    private final CategorieRepository categorieRepository;
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public ResponseCategorieDTO create(CreateCategorieDTO createCategorieDTO) {
        Categorie categorieDesi = categorieRepository.findByDesignation(createCategorieDTO.designation());
        if(categorieDesi != null) throw new DuplicateDesignationException("designation already in use");
        Categorie categorie = categorieMapper.fromCreate(createCategorieDTO);
        Categorie categorieSaved = categorieRepository.save(categorie);
        return categorieMapper.toResponse(categorieSaved);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public ResponseCategorieDTO update(Long id, CreateCategorieDTO createCategorieDTO)  {
        Categorie categorieOld = categorieRepository.findById(id).orElseThrow(() -> new NotFoundCategorieException("not found categorie"));
        Categorie categorieDesi = categorieRepository.findByDesignation(createCategorieDTO.designation());
        if(categorieOld != categorieDesi) throw new DuplicateDesignationException("designation already in use");
        categorieOld.setDesignation(categorieOld.getDesignation());
        Categorie categorieSaved = categorieRepository.save(categorieOld);
        return categorieMapper.toResponse(categorieSaved);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public void delete(Long id)  {
        Categorie categorie = categorieRepository.findById(id).orElseThrow(() -> new NotFoundCategorieException("not found categorie"));
        categorieRepository.delete(categorie);
    }

    @Override
    public ResponseCategorieDTO findById(Long id)  {
        Categorie categorie = categorieRepository.findById(id).orElseThrow(() -> new NotFoundCategorieException("not found categorie"));
        return categorieMapper.toResponse(categorie);
    }

    @Override
    public ResponseCategorieDTO findByDesignation(String designation)  {
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
