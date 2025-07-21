package ma.ensa.test_stage_projet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Utilisateur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(columnDefinition = "NUMBER(14)")
    private Operateur operateur;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<DeclarationImportation> declarationImportations;
    @ManyToOne(fetch = FetchType.EAGER)
    private Profile profile;

}
