package ma.ensa.test_stage_projet.repositories;

import ma.ensa.test_stage_projet.entities.ArticleImport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleImportRepository extends JpaRepository<ArticleImport, Long> {
}
