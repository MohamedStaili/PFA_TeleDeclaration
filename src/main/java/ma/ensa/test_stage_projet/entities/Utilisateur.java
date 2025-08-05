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
    private String email;
    private String password;
    private boolean active = false;
    private String tokenActivation ;
    private String passwordResetToken;
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
    @ManyToOne
    //@JoinColumn(columnDefinition = "NUMBER(14)")
    private Operateur operateur;
    @OneToMany(mappedBy = "user")
    private List<DeclarationImportation> declarationImportations;
    @ManyToOne
    private Profile profile;
    @ManyToOne
    Site site;
}
