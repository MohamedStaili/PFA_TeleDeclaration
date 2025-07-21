package ma.ensa.test_stage_projet.repositories;

import ma.ensa.test_stage_projet.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
