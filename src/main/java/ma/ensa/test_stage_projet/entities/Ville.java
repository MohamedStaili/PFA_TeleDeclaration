package ma.ensa.test_stage_projet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Ville {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "NUMBER(10)")
    private Long id_ville ;
    @Column(length = 255)
    private String code ;
    @Column(length = 255)
    private String designation ;
    @OneToOne(mappedBy = "adresse", fetch = FetchType.EAGER)
    private ServiceExterieur adresse_se ;  //cette ville est sous quelle SE
    @JoinColumn(name = "ID_SERVICE_EXTERIEUR",columnDefinition = "NUMBER(10)")
    @ManyToOne(fetch = FetchType.EAGER)
    private ServiceExterieur serviceExterieur;
    @OneToMany(mappedBy = "ville")
    private List<Operateur> operateurs ;
    @OneToMany(mappedBy = "ville" , fetch = FetchType.LAZY)
    private List<PortDechargemnt> portDechargemnt ;

}
