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
@Data @NoArgsConstructor @AllArgsConstructor
public class Ville {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(columnDefinition = "NUMBER(10)")
    private Long id_ville ;
    @Column(length = 255)
    private String code ;
    @Column(length = 255)
    private String designation ;
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
//    @OneToOne(mappedBy = "adresse", fetch = FetchType.EAGER)
//    private ServiceExterieur adresse_se ;
    @JoinColumn(name = "ID_SERVICE_EXTERIEUR",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private ServiceExterieur serviceExterieur;//cette ville est sous quelle SE
//    @OneToMany(mappedBy = "ville")
//    private List<Operateur> operateurs ;
//    @OneToMany(mappedBy = "ville" , fetch = FetchType.LAZY)
//    private List<PortDechargemnt> portDechargemnt ;

}
