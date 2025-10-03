package ma.ensa.test_stage_projet.mappers;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateDeclarationDTO;
import ma.ensa.test_stage_projet.entities.ArticleImport;
import ma.ensa.test_stage_projet.entities.DeclarationImportation;
import ma.ensa.test_stage_projet.entities.PortDechargemnt;
import ma.ensa.test_stage_projet.entities.RegimeImportation;
import ma.ensa.test_stage_projet.exceptions.NotFoundArticleException;
import ma.ensa.test_stage_projet.exceptions.NotFoundPortException;
import ma.ensa.test_stage_projet.exceptions.NotFoundRegimeException;
import ma.ensa.test_stage_projet.repositories.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeclarationMapper {
    private final RegimeImportationRepository regimeImportationRepository;
    private final PortDechargementRepository portDechargementRepository;
    private final ArticleImportRepository articleImportRepository;

    public DeclarationImportation fromCreate(CreateDeclarationDTO dto) throws NotFoundRegimeException, NotFoundPortException, NotFoundArticleException {
        RegimeImportation regimeImportation = regimeImportationRepository.findById(dto.regime())
                .orElseThrow(() -> new NotFoundRegimeException("regime not found with id " + dto.regime()));
        PortDechargemnt port1 = portDechargementRepository.findById(dto.port1())
                .orElseThrow(() -> new NotFoundPortException("port not found with id " + dto.port1()));
        PortDechargemnt port2 = null;
        if(dto.port2()!=null) {
            port2 = portDechargementRepository.findById(dto.port2())
                    .orElseThrow(() -> new NotFoundPortException("port not found with id " + dto.port2()));
        }
        
        ArticleImport articleImport = articleImportRepository.findById(dto.article())
                .orElseThrow(() -> new NotFoundArticleException("article not found with id " + dto.article()));

        DeclarationImportation declarationImportation = new DeclarationImportation();
        declarationImportation.setArticleImport(articleImport);
        declarationImportation.setRegimeImportation(regimeImportation);
        declarationImportation.setPortDecharPrincipal(port1);
        if(dto.port2()!=null) declarationImportation.setPortDecharSecondaire(port2);
        declarationImportation.setCompteTiers(dto.compteTiers());
        declarationImportation.setQte(dto.qte());
        declarationImportation.setDate_limite_arrive(dto.dateLimitArr());
        return declarationImportation ;
    }
}
