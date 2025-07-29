package ma.ensa.test_stage_projet.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateArticleDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseArticleDTO;
import ma.ensa.test_stage_projet.exceptions.NotFoundArticleException;
import ma.ensa.test_stage_projet.exceptions.NotFoundCategorieException;
import ma.ensa.test_stage_projet.exceptions.NotFoundRegimeException;
import ma.ensa.test_stage_projet.services.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/articles-imporatations")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping()
    public ResponseEntity<?> saveArticle(@RequestBody @Valid CreateArticleDTO createArticleDTO) {
        Map<String,Object> response = new HashMap<>();
        try{
            ResponseArticleDTO responseArticleDTO = articleService.addArticle(createArticleDTO);
            response.put("message","article added");
            response.put("article",responseArticleDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (NotFoundCategorieException | NotFoundRegimeException e) {
            response.put("message","error");
            response.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id , @RequestBody @Valid CreateArticleDTO createArticleDTO){
        Map<String,Object> response = new HashMap<>();
        try{
            ResponseArticleDTO responseArticleDTO = articleService.updateArticle(id,createArticleDTO);
            response.put("message","article updated");
            response.put("article",responseArticleDTO);
            return ResponseEntity.ok(response);
        }catch (NotFoundRegimeException | NotFoundCategorieException | NotFoundArticleException e) {
            response.put("message","error");
            response.put("error",e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id){
        Map<String,Object> response = new HashMap<>();
        try {
            articleService.deleteArticle(id);
            response.put("message","article deleted");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        } catch (NotFoundArticleException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllArticles(){
        Map<String,Object> response = new HashMap<>();
        List<ResponseArticleDTO> responseArticleDTOS = articleService.getArticles();
        response.put("articles",responseArticleDTOS);
        return ResponseEntity.ok(response);
    }

    @GetMapping()
    public ResponseEntity<?> getArticle(@RequestParam(required = false) Long id ,
                                        @RequestParam(required = false) String designation){
        Map<String,Object> response = new HashMap<>();
      try{
          long nbParams = Stream.of( id , designation ).filter(Objects::nonNull).count();
          if(nbParams !=1) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nombre des parametres non autorise");
          ResponseArticleDTO responseArticleDTO =
                  switch ((int) nbParams){
                      case 1 -> {
                          if(id != null) yield articleService.getArticle(id);
                          yield articleService.getArticleByDesignation(designation);
                      }
                      default -> throw new IllegalStateException("Unexpected value: " + nbParams);
                  };
          response.put("article",responseArticleDTO);
          return ResponseEntity.ok(response);

      } catch (NotFoundArticleException e) {
          response.put("message","error");
          response.put("error",e.getMessage());
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
      }
    }

}
