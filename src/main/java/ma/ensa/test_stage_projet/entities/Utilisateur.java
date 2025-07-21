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
public class Utilisateur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
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
    @ManyToOne(fetch = FetchType.EAGER)
    //@JoinColumn(columnDefinition = "NUMBER(14)")
    private Operateur operateur;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<DeclarationImportation> declarationImportations;
    @ManyToOne(fetch = FetchType.EAGER)
    private Profile profile;
    @ManyToOne
    Site site;


}
