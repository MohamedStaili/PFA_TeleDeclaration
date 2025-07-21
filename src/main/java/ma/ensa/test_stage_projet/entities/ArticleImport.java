package ma.ensa.test_stage_projet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class ArticleImport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "NUMBER(14)")
    private Long id ;
    @Column(length = 50)
    private String designation_dc ;
    @Column(length = 10)
    private String ngp ;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(columnDefinition = "NUMBER(14)")
    private RegimeImportation regime_import ;
    @OneToMany(mappedBy = "articleImport", fetch = FetchType.EAGER)
    @JoinColumn(columnDefinition = "NUMBER(14)")
    private List<DeclarationImportation> declaration_imports ;
    @ManyToOne(fetch = FetchType.EAGER)
    private Categorie categorie ;
}
