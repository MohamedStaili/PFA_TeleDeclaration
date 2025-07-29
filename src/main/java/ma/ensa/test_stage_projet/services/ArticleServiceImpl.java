package ma.ensa.test_stage_projet.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateArticleDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseArticleDTO;
import ma.ensa.test_stage_projet.entities.ArticleImport;
import ma.ensa.test_stage_projet.exceptions.NotFoundArticleException;
import ma.ensa.test_stage_projet.exceptions.NotFoundCategorieException;
import ma.ensa.test_stage_projet.exceptions.NotFoundRegimeException;
import ma.ensa.test_stage_projet.mappers.ArticleMapper;
import ma.ensa.test_stage_projet.repositories.ArticleImportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    private final ArticleImportRepository articleImportRepository;
    private final ArticleMapper articleMapper;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseArticleDTO addArticle(CreateArticleDTO createArticleDTO) throws NotFoundRegimeException, NotFoundCategorieException {
        ArticleImport articleImport = articleMapper.fromCreate(createArticleDTO);
        ArticleImport savedArticleImport = articleImportRepository.save(articleImport);
        return articleMapper.toResponse(savedArticleImport);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseArticleDTO updateArticle(Long id, CreateArticleDTO createArticleDTO) throws NotFoundArticleException, NotFoundRegimeException, NotFoundCategorieException {
        ArticleImport articleImport = articleImportRepository.findById(id)
                .orElseThrow(() -> new NotFoundArticleException("not found article"));
        ArticleImport articleImport1 = articleMapper.fromCreate(createArticleDTO);
        articleImport1.setId(articleImport.getId());
        ArticleImport savedArticleImport = articleImportRepository.save(articleImport1);
        return articleMapper.toResponse(savedArticleImport);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteArticle(Long id) throws NotFoundArticleException {
        ArticleImport articleImport = articleImportRepository.findById(id)
                .orElseThrow(() -> new NotFoundArticleException("not found article"));
       articleImportRepository.delete(articleImport);
    }

    @Override
    public ResponseArticleDTO getArticle(Long id) throws NotFoundArticleException {
        ArticleImport articleImport = articleImportRepository.findById(id)
                .orElseThrow(() -> new NotFoundArticleException("not found article"));
        return articleMapper.toResponse(articleImport);
    }

    @Override
    public ResponseArticleDTO getArticleByDesignation(String designation) throws NotFoundArticleException {
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
