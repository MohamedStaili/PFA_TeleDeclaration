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

public class RegimeImportation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(columnDefinition = "Number(10)")
    private Long id_regime_impor ;
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
    @OneToMany(mappedBy = "regime_import" , fetch = FetchType.LAZY)
    private List<ArticleImport> articleImport ;
//    @OneToMany(mappedBy = "regimeImportation", fetch = FetchType.LAZY)
//    private List<DeclarationImportation> declarationImportation ;
}
