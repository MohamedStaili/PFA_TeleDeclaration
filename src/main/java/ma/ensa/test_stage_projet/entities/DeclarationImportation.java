package ma.ensa.test_stage_projet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class DeclarationImportation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "Number(14)")
    private Long id_declaration ;
    @JoinColumn(columnDefinition = "NUMBER(14)")
    @ManyToOne(fetch = FetchType.EAGER)
    private Operateur operateur_imporatateur;
    @JoinColumn(columnDefinition = "NUMBER(14)")
    @ManyToOne(fetch = FetchType.EAGER)
    private ServiceExterieur serviceExterieur;
    @JoinColumn(columnDefinition = "NUMBER(14)")
    @ManyToOne(fetch = FetchType.EAGER)
    private RegimeImportation regimeImportation;
    @Column(length = 50)
    private String ref_declaration ;
    @Temporal(TemporalType.DATE)
    private Date date_declaration ; // pour la date declaration de l'operateur chez
    //l'onicl pour la premiere fois
    @Temporal(TemporalType.DATE)
    private Date date_saisie; //date de saisie de la demande
    @Temporal(TemporalType.DATE)
    private Date date_limite_arrive;
    @Enumerated(EnumType.STRING)
    @Column(length = 15)
    private Statut statut ;
    @Column(length = 10)
    private String deja_import ;
    @Column(length = 80)
    private Long num_recepisse ;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(columnDefinition = "NUMBER(14)")
    private Utilisateur user ;
    @Column(precision = 14 , scale = 3)
    private BigDecimal mnt_caution ;
    @Column(length = 50)
    private String ref_caution ;
    @JoinColumn(columnDefinition = "NUMBER(14)")
    @OneToOne(fetch = FetchType.EAGER)
    private PortDechargemnt portDecharPrincipal  ;
    @JoinColumn(columnDefinition = "NUMBER(14)")
    @OneToOne(fetch = FetchType.EAGER)
    private PortDechargemnt portDecharSecondaire ;
    @Column(length = 14)
    private int nbjourArriveNavire ;
    @JoinColumn(columnDefinition = "NUMBER(14)")
    @ManyToOne(fetch = FetchType.EAGER)
    private ArticleImport articleImport ;
    @Temporal(TemporalType.DATE)
    private Date dateEnvoiOper ;
    @Column(precision = 14 , scale = 3)
    private BigDecimal qte ;
    private Integer compteTiers ;
    @Temporal(TemporalType.DATE)
    private Date delevranceR ;
    @Temporal(TemporalType.DATE)
    private Date datePassDouan ;
}
