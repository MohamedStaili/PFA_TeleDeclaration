package ma.ensa.test_stage_projet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class PortDechargemnt {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 10)
    private Long id_port;
    @Column(length = 40)
    private String code ;
    @Column(length = 320)
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
    @JoinColumn(name = "ville_id",nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Ville ville;
    @JoinColumn(name = "SE_ID", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private ServiceExterieur serviceExterieur;
//    @OneToOne(mappedBy = "portDecharPrincipal" , fetch = FetchType.LAZY)
//    private DeclarationImportation declaration_importation_1;
//    @OneToOne(mappedBy = "portDecharSecondaire" , fetch = FetchType.LAZY)
//    private DeclarationImportation declaration_importation_2;

}
