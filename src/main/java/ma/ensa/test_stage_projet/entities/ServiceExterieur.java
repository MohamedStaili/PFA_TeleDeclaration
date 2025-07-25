package ma.ensa.test_stage_projet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class ServiceExterieur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSE;

    @CreationTimestamp
    @Column(name = "CREE_LE")
    private Date creeLe;

    @Column(name = "CREE_PAR", length = 30)
    private String creePar;

    @UpdateTimestamp
    @Column(name = "MODIFIE_LE")
    private Date modifieLe;

    @Column(name = "MODIFIE_PAR", length = 30)
    private String modifiePar;

    @Column(length = 255, unique = true, nullable = false)
    private String codeSE;

    @Column(length = 255, unique = true, nullable = false)
    private String nomSE;


    @Column(name = "ID_VILLE_ADRESSE")
    private Long adresseVilleId;


    @OneToMany(mappedBy = "serviceExterieur", cascade = {CascadeType.PERSIST , CascadeType.MERGE ,CascadeType.REFRESH , CascadeType.DETACH}, fetch = FetchType.LAZY)
    private List<Ville> villes = new ArrayList<>();


    @Transient
    public Ville getAdresse() {
        if (adresseVilleId != null && villes != null) {
            return villes.stream()
                    .filter(ville -> ville.getId_ville().equals(adresseVilleId))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    public void addVille(Ville ville) {
        villes.add(ville);
        ville.setServiceExterieur(this);
    }

    public void removeVille(Ville ville) {
        villes.remove(ville);
        ville.setServiceExterieur(null);

        if (adresseVilleId != null && adresseVilleId.equals(ville.getId_ville())) {
            adresseVilleId = null;
        }
    }
}