package ma.ensa.test_stage_projet.entities;



import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "SITE")
public class Site {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SITE")
    private Long idSite;

//    @Column(name = "ID_OPERATEUR")
//    private  Long idOperateur;

    @Column(name = "ID_COMM")
    private Long idComm;

//    @Column(name = "ID_SE")
//    private Long idSe;

    @Column(name = "CODE", length = 40)
    private String code;

    @Column(name = "NOM_SITE", length = 320)
    private String nomSite;

    @Column(name = "ADRESSE", length = 800)
    private String adresse;

    @Column(name = "STATUT", length = 100)
    private String statut;

//    @Column(name = "ID_OPERATEUR_SITE")
//    private Long idOperateurSite;

//    @Column(name = "ID_OPERATEUR_UNITE")
//    private Long idOperateurUnite;

//    @Column(name = "ID_PROVINCE")
//    private Double idProvince;

    @Column(name = "LAT_GPS", length = 40)
    private String latGps;

    @Column(name = "LONG_GPS", length = 40)
    private String longGps;

//    @Column(name = "ID_VILLE")
//    private Long idVille;

    @Column(name = "IDX")
    private Integer idx;

    @Column(name = "COMMENTAIRE", length = 600)
    private String commentaire;

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
    @JoinColumn(name = "ID_OPERATEUR",nullable = false)
    private Operateur operateur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SE")
    private ServiceExterieur se;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VILLE",nullable = false)
    private Ville ville;
    @OneToMany(mappedBy = "site")
    private java.util.List<Utilisateur> users;
}
