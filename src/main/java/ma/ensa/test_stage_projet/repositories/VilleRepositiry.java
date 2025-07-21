package ma.ensa.test_stage_projet.repositories;

import ma.ensa.test_stage_projet.entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VilleRepositiry extends JpaRepository<Ville, Long> {
}
