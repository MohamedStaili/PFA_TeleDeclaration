package ma.ensa.test_stage_projet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.ensa.test_stage_projet.repositories.OperateurRepository;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;
import java.util.List;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
public class Operateur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(columnDefinition = "NUMBER(10)")
    private Long id_operateur;
    @Column(length = 40 , unique = true , nullable = false)
    private String code ;
    @Column(length = 320)
    private String raison_soc ;
    @Column(length = 10 ,unique = true , nullable = false )
    private Long codeCptable ;
    @Column(length = 1)
    private Long actif ;
    @Column(length = 10)
    private Long profil ;
    @Column(length = 20)
    private String num_patenete ;
    @Column(length = 20)
    private String registre_com ;
    @Column(length = 120)
    private String adresse ;
    @Column(length = 100)
    private String ice ;
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
    @JoinColumn(name = "id_ville", nullable = false)
    @ManyToOne
    private Ville ville;
    @OneToMany(mappedBy = "operateur_imporatateur" , fetch = FetchType.LAZY)
    private List<DeclarationImportation> declaration_importations;
    @OneToMany(mappedBy = "operateur", fetch = FetchType.EAGER)
    private List<Utilisateur> users;

}
