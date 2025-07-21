package ma.ensa.test_stage_projet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JoinColumn(columnDefinition = "NUMBER(10)")
    @ManyToOne(fetch = FetchType.EAGER)
    private Ville ville;
    @JoinColumn(columnDefinition = "NUMBER(10)")
    @ManyToOne(fetch = FetchType.EAGER)
    private ServiceExterieur serviceExterieur;
    @OneToOne(mappedBy = "portDecharPrincipal" , fetch = FetchType.LAZY)
    private DeclarationImportation declaration_importation_1;
    @OneToOne(mappedBy = "portDecharSecondaire" , fetch = FetchType.LAZY)
    private DeclarationImportation declaration_importation_2;

}
