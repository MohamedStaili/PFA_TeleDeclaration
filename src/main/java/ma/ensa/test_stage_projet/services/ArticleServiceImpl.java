package ma.ensa.test_stage_projet.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateArticleDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseArticleDTO;
import ma.ensa.test_stage_projet.entities.ArticleImport;
import ma.ensa.test_stage_projet.exceptions.DuplicateDesignationException;
import ma.ensa.test_stage_projet.exceptions.NotFoundArticleException;
import ma.ensa.test_stage_projet.exceptions.NotFoundCategorieException;
import ma.ensa.test_stage_projet.exceptions.NotFoundRegimeException;
import ma.ensa.test_stage_projet.mappers.ArticleMapper;
import ma.ensa.test_stage_projet.repositories.ArticleImportRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleImportRepository articleImportRepository;
    private final ArticleMapper articleMapper;
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public ResponseArticleDTO addArticle(CreateArticleDTO createArticleDTO)
            throws NotFoundRegimeException, NotFoundCategorieException {
        ArticleImport articleDesi = articleImportRepository.findByDesignationDc(createArticleDTO.designation());
        if(articleDesi != null) throw new DuplicateDesignationException("designation already in use");
        ArticleImport articleImport = articleMapper.fromCreate(createArticleDTO);
        ArticleImport savedArticleImport = articleImportRepository.save(articleImport);
        return articleMapper.toResponse(savedArticleImport);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public ResponseArticleDTO updateArticle(Long id, CreateArticleDTO createArticleDTO)
             {
        ArticleImport articleImport = articleImportRepository.findById(id)
                .orElseThrow(() -> new NotFoundArticleException("not found article"));
        ArticleImport articleDesi = articleImportRepository.findByDesignationDc(createArticleDTO.designation());
        if(articleImport != articleDesi ) throw new DuplicateDesignationException("designation already in use");
        ArticleImport articleImport1 = articleMapper.fromCreate(createArticleDTO);
        articleImport1.setId(articleImport.getId());
        ArticleImport savedArticleImport = articleImportRepository.save(articleImport1);
        return articleMapper.toResponse(savedArticleImport);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public void deleteArticle(Long id)  {
        ArticleImport articleImport = articleImportRepository.findById(id)
                .orElseThrow(() -> new NotFoundArticleException("not found article"));
       articleImportRepository.delete(articleImport);
    }

    @Override
    public ResponseArticleDTO getArticle(Long id)  {
        ArticleImport articleImport = articleImportRepository.findById(id)
                .orElseThrow(() -> new NotFoundArticleException("not found article"));
        return articleMapper.toResponse(articleImport);
    }

    @Override
    public ResponseArticleDTO getArticleByDesignation(String designation)  {
        ArticleImport articleImport = articleImportRepository.findByDesignationDc(designation);
        if (articleImport == null) throw new NotFoundArticleException("not found article");
        return articleMapper.toResponse(articleImport);
    }

    @Override
    public List<ResponseArticleDTO> getArticles() {
        List<ArticleImport> articleImports = articleImportRepository.findAll();
        return articleImports.stream().map(articleMapper::toResponse).collect(Collectors.toList());
    }
}
