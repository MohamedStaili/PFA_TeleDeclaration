package ma.ensa.test_stage_projet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class ServiceExterieur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(columnDefinition = "Number(10)")
    private Long id_se;
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
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VILLE")
    private Ville adresse; //pour l'adresse de la service exterieur
    @OneToMany(mappedBy = "serviceExterieur", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Ville> villes;
    @Column(length = 255, unique = true , nullable = false)
    private String codeSE;
    @Column(length = 255, unique = true , nullable = false)
    private String nomSE;
//    @OneToMany(mappedBy = "serviceExterieur", fetch = FetchType.LAZY)
//    private List<PortDechargemnt> portDechargemnt;
//    @OneToMany(mappedBy = "serviceExterieur" , fetch = FetchType.LAZY)
//    private List<DeclarationImportation> declarationImportation;

}
