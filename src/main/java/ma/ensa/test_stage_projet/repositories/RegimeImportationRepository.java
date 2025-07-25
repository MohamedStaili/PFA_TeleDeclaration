package ma.ensa.test_stage_projet.repositories;

import ma.ensa.test_stage_projet.Dtos.ResponseRegimeDTO;
import ma.ensa.test_stage_projet.entities.RegimeImportation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegimeImportationRepository extends JpaRepository<RegimeImportation, Long> {
    RegimeImportation findByCode(String code);
    RegimeImportation findByDesignation(String designation);
}
