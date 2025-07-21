package ma.ensa.test_stage_projet;

import ma.ensa.test_stage_projet.entities.*;
import ma.ensa.test_stage_projet.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Stream;

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
                                               PortDechargementRepository portDechargementRepository,
                                                CategorieRepository categorieRepository

                                               ) {
        return args -> {
            Integer code =0;
//            Stream.of("Rabat","Sale","Temara","Kenitra","Sidi Kacem","Sidi slimane")
//                    .forEach(name -> {
//                        Ville ville = new Ville();
//                        ville.setCode(Integer.toString(code));
//                        ville.setDesignation(name);
//                       villeRepositiry.save(ville);

//                    });
//            Ville ville =villeRepositiry.findById(1L).orElseThrow(()-> new RuntimeException("Ville n'existe pas"));
//            ServiceExterieur serviceExterieur = new ServiceExterieur();
//            serviceExterieur.setNom_se("Centrale");
//            serviceExterieur.setAdresse(ville);
//            serviceExterieur.setCode_se("1");
//            serviceRepository.save(serviceExterieur);
//              ServiceExterieur serviceExterieur = serviceRepository.findById(1L).orElse(null);
//              List<Ville> villes = villeRepositiry.findAll();
//              villes.forEach(ville -> {
//                  ville.setServiceExterieur(serviceExterieur);
//                  villeRepositiry.save(ville);
//              });
//            RegimeImportation regimeImportation = new RegimeImportation();
//            regimeImportation.setDesignation("Importation libre");
//            regimeImportation.setCode("001");
//            RegimeImportation regimeImportation1 = new RegimeImportation();
//            regimeImportation1.setDesignation("Admission Temporaire");
//            regimeImportation1.setCode("002");
//            regimeImportationRepository.saveAll(List.of(regimeImportation, regimeImportation1));
//            Categorie categorie = new Categorie();
//            categorie.setDesignation("Cereales");
//            Categorie categorie2 = new Categorie();
//            categorie2.setDesignation("Legumineuse");
//            categorieRepository.saveAll(List.of(categorie, categorie2));
//            Categorie categorie = categorieRepository.findById(1L).orElse(null);
//            Categorie categorie1 =categorieRepository.findById(2L).orElse(null);
//            RegimeImportation regimeImportation = regimeImportationRepository.findById(1L).orElse(null);
//            RegimeImportation regimeImportation2 = regimeImportationRepository.findById(2L).orElse(null);
//
//            ArticleImport articleImport = new ArticleImport();
//            articleImport.setRegime_import(regimeImportation);
//            articleImport.setDesignation_dc("Lupin");
//            articleImport.setCategorie(categorie);
//            ArticleImport articleImport2 = new ArticleImport();
//            articleImport2.setRegime_import(regimeImportation2);
//            articleImport2.setDesignation_dc("Lupin");
//            articleImport2.setCategorie(categorie1);
//            articleImportRepository.saveAll(List.of(articleImport, articleImport2));
//            Ville ville = villeRepositiry.findById(4L).orElse(null);
//            ServiceExterieur serviceExterieur = serviceRepository.findById(1L).orElse(null);
//            PortDechargemnt portDechargemnt = new PortDechargemnt();
//            portDechargemnt.setDesignation("Kenitra");
//            portDechargemnt.setVille(ville);
//            portDechargemnt.setServiceExterieur(serviceExterieur);
//            portDechargementRepository.save(portDechargemnt);






        };
    }
}
