package ma.ensa.test_stage_projet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
public class Operateur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "NUMBER(10)")
    private Long id_operateur;
    @JoinColumn(columnDefinition = "NUMBER(10)")
    @ManyToOne(fetch = FetchType.EAGER)
    private Ville ville;
    @Column(length = 40)
    private String code ;
    @Column(length = 320)
    private String raison_soc ;
    @Column(length = 10)
    private Long code_cptable ;
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
    @OneToMany(mappedBy = "operateur_imporatateur" , fetch = FetchType.LAZY)
    private List<DeclarationImportation> declaration_importations;
    @OneToMany(mappedBy = "operateur", fetch = FetchType.LAZY)
    private List<Utilisateur> users;

}
