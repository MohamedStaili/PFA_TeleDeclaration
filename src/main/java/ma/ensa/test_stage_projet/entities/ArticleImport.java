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
public class ArticleImport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(columnDefinition = "NUMBER(14)")
    private Long id ;
    @Column(length = 50)
    private String designationDc ;
    @Column(length = 10)
    private String ngp ;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regime_id")
    private RegimeImportation regime_import ;
//    @OneToMany(mappedBy = "articleImport", fetch = FetchType.EAGER)
//    @JoinColumn(columnDefinition = "NUMBER(14)")
//    private List<DeclarationImportation> declaration_imports ;
    @JoinColumn(name = "categorie_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Categorie categorie ;
}
