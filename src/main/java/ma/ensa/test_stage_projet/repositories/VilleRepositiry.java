package ma.ensa.test_stage_projet.repositories;

import ma.ensa.test_stage_projet.entities.ServiceExterieur;
import ma.ensa.test_stage_projet.entities.Ville;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VilleRepositiry extends JpaRepository<Ville, Long> {
    Ville findByDesignation(String nom);
    Page<Ville> findAll(Pageable pageable);
    //Page<Ville> findByServiceExterieur(ServiceExterieur serviceExterieur,Pageable pageable);
    Ville findByCode(String code);
}
