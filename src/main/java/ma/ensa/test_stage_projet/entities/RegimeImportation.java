package ma.ensa.test_stage_projet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor

public class RegimeImportation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "Number(10)")
    private Long id_regime_impor ;
    @Column(length = 40)
    private String code ;
    @Column(length = 320)
    private String designation ;
    @OneToMany(mappedBy = "regime_import" , fetch = FetchType.LAZY)
    private List<ArticleImport> articleImport ;
    @OneToMany(mappedBy = "regimeImportation", fetch = FetchType.LAZY)
    private List<DeclarationImportation> declarationImportation ;
}
