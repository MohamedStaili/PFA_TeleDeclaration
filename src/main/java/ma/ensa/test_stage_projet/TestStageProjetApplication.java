package ma.ensa.test_stage_projet;

import ma.ensa.test_stage_projet.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestStageProjetApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestStageProjetApplication.class, args);
    }

    //@Bean
    public CommandLineRunner commandLineRunner(ServiceExterieurRepository serviceRepository,
                                               VilleRepositiry villeRepositiry,
                                               RegimeImportationRepository regimeImportationRepository,
                                               ArticleImportRepository articleImportRepository,
                                               PortDechargementRepository portDechargementRepository

    ) {
        return args -> {
//            Ville villeAdresse = new Ville();
//            villeAdresse.setDesignation("Laayoune");
//            villeAdresse.setCode("016");
//            //creer les villes
//            Ville villeRattachement2 = new Ville();
//            villeRattachement2.setDesignation("Dakjla");
//            villeRattachement2.setCode("017");
//            Ville villeRattachement3 = new Ville();
//            villeRattachement3.setDesignation("Semara");
//            villeRattachement3.setCode("018");
//            //creer les services
//            ServiceExterieur serviceExterieur = new ServiceExterieur();
//            serviceExterieur.setCode_se("016");
//            serviceExterieur.setAdresse(villeAdresse);
//            //ajouter les services au villes
//            villeAdresse.setServiceExterieur(serviceExterieur);
//            villeRattachement2.setServiceExterieur(serviceExterieur);
//            villeRattachement3.setServiceExterieur(serviceExterieur);
//
//            //ajouetr les villes au services
//            serviceExterieur.setVilles(List.of(villeAdresse,villeRattachement2, villeRattachement3));
//            serviceExterieur.setNom_se(serviceExterieur.getAdresse().getDesignation());
//            //sauvegarder seulement la service cascade va sauvegarder les villes
//            serviceRepository.save(serviceExterieur);


//            RegimeImportation regimeImportation1 = new RegimeImportation();
//            regimeImportation1.setCode("01");
//            regimeImportation1.setDesignation("Importation libre");
//
//            RegimeImportation regimeImportation2 = new RegimeImportation();
//            regimeImportation2.setCode("02");
//            regimeImportation2.setDesignation("Admission temporaire");
//            regimeImportationRepository.saveAll(List.of(regimeImportation1, regimeImportation2));
//            RegimeImportation regimeImportation1 = regimeImportationRepository.findById(1L).orElse(null);
//            RegimeImportation regimeImportation2 = regimeImportationRepository.findById(2L).orElse(null);
//
//            Stream.of("Blé tendre","Blé dur","Maïs","Pois chiches secs" ,"Lentilles").forEach(produits -> {
//
//                ArticleImport articleImport = new ArticleImport();
//                articleImport.setDesignation_dc(produits);
//                articleImport.setRegime_import(regimeImportation1);
//                articleImportRepository.save(articleImport);
//            });
//            Stream.of("Blé tendre","Blé dur","Maïs","Pois chiches secs" ,"Lentilles").forEach(produits -> {
//
//                ArticleImport articleImport = new ArticleImport();
//                articleImport.setDesignation_dc(produits);
//                articleImport.setRegime_import(regimeImportation2);
//                articleImportRepository.save(articleImport);
//            });
//            List<Ville> villes =villeRepositiry.findAll();
//            villes.forEach(ville -> {
//                PortDechargemnt portDechargemnt = new PortDechargemnt();
//                portDechargemnt.setVille(ville);
//                portDechargemnt.setServiceExterieur(ville.getServiceExterieur());
//                portDechargemnt.setCode(ville.getCode());
//                portDechargemnt.setDesignation(ville.getDesignation());
//                portDechargementRepository.save(portDechargemnt);
//
//            });







        };
    }
}
