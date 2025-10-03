package ma.ensa.test_stage_projet.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateDeclarationDTO;
import ma.ensa.test_stage_projet.entities.DeclarationImportation;
import ma.ensa.test_stage_projet.entities.Operateur;
import ma.ensa.test_stage_projet.entities.ServiceExterieur;
import ma.ensa.test_stage_projet.entities.Utilisateur;
import ma.ensa.test_stage_projet.exceptions.*;
import ma.ensa.test_stage_projet.mappers.DeclarationMapper;
import ma.ensa.test_stage_projet.repositories.DeclarationImporatationRepository;
import ma.ensa.test_stage_projet.repositories.OperateurRepository;
import ma.ensa.test_stage_projet.repositories.ServiceExterieurRepository;
import ma.ensa.test_stage_projet.repositories.UtilisateurRepository;
import ma.ensa.test_stage_projet.sec.UserDetailsInfo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class DeclarationServiceImpl implements DeclarationService {
    private final DeclarationImporatationRepository declarationImporatationRepository ;
    private final OperateurRepository operateurRepository ;
    private final UtilisateurRepository utilisateurRepository ;
    private final DeclarationMapper declarationMapper;
    @PreAuthorize("hasRole('IMPORTATEUR')")
    @Transactional
    @Override
    public void create(CreateDeclarationDTO dto , UserDetailsInfo user){
    DeclarationImportation declarationImportation = declarationMapper.fromCreate(dto) ;
        Operateur operateur = operateurRepository.findById(user.getOperateur())
                .orElseThrow(()-> new NotFoundOperateurException("not found operateur with id" + user.getOperateur()));
        Utilisateur utilisateur = utilisateurRepository.findByEmail(user.getUsername()) ;
        if (utilisateur == null) throw new NotFoundUtilisateur("not found utilisateur with email" + user.getUsername());
        ServiceExterieur serviceExterieur = operateur.getVille().getServiceExterieur();
        Date dateSaisie = new Date();
        Calendar cal  = Calendar.getInstance();
        cal.setTime(dateSaisie);
        cal.add(Calendar.DAY_OF_MONTH, 90);
        Date dateSaisiePlus90Days = cal.getTime();
        Date dateLimit = declarationImportation.getDate_limite_arrive() ;
        if(dateSaisie.after(dateLimit)){
            throw new DateExpiredException("veuillez saisir une date futur");
        }
        if(dateLimit.after(dateSaisiePlus90Days)){
            throw new DateTuMuchException("la date d'arrive ne doit pas dépasse 90 jours ") ;
        }
        declarationImportation.setOperateur_imporatateur(operateur);
        declarationImportation.setDate_declaration(operateur.getCreeLe());
        declarationImportation.setServiceExterieur(serviceExterieur);
        declarationImportation.setUser(utilisateur);
        declarationImportation.setDate_saisie(dateSaisie);
        declarationImporatationRepository.save(declarationImportation);
    }


}
