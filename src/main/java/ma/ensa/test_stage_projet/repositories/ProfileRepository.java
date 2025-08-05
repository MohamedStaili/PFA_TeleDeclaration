package ma.ensa.test_stage_projet.repositories;

import ma.ensa.test_stage_projet.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Profile findByNom(String nom);
}
