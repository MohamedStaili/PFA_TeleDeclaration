package ma.ensa.test_stage_projet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class ServiceExterieur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "Number(10)")
    private Long id_se;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_VILLE",columnDefinition = "NUMBER(10)")
    private Ville adresse; //pour l'adresse de la service exterieur
    @OneToMany(mappedBy = "serviceExterieur", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Ville> villes;
    @Column(length = 255)
    private String code_se;
    @Column(length = 255)
    private String nom_se;
    @OneToMany(mappedBy = "serviceExterieur", fetch = FetchType.LAZY)
    private List<PortDechargemnt> portDechargemnt;
    @OneToMany(mappedBy = "serviceExterieur" , fetch = FetchType.LAZY)
    private List<DeclarationImportation> declarationImportation;
}
