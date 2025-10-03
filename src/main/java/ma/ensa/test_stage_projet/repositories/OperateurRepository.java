package ma.ensa.test_stage_projet.repositories;

import jakarta.persistence.Entity;
import ma.ensa.test_stage_projet.entities.Operateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperateurRepository extends JpaRepository<Operateur, Long> {
    Optional<Operateur> findTopByOrderByCodeCptableDesc();
    Operateur findByCodeCptable(Long codeCptable);

    Operateur findByCode(String code);
}
