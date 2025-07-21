package ma.ensa.test_stage_projet.repositories;

import ma.ensa.test_stage_projet.entities.PortDechargemnt;
import ma.ensa.test_stage_projet.entities.RegimeImportation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortDechargementRepository extends JpaRepository<PortDechargemnt, Long> {
}
