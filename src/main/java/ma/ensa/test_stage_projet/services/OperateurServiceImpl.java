package ma.ensa.test_stage_projet.services;
import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateOperatuerDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseOperateurDTO;
import ma.ensa.test_stage_projet.entities.Operateur;
import ma.ensa.test_stage_projet.entities.Profile;
import ma.ensa.test_stage_projet.exceptions.NotFoundOperateurException;
import ma.ensa.test_stage_projet.exceptions.NotFoundProfileException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;
import ma.ensa.test_stage_projet.mappers.OperateurMapper;
import ma.ensa.test_stage_projet.repositories.OperateurRepository;
import ma.ensa.test_stage_projet.repositories.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperateurServiceImpl implements OperateurService {
    private final OperateurRepository operateurRepository;
    private final OperateurMapper operateurMapper;
    private final ProfileRepository profileRepository;
    private long generateNextCode() {
        String lastCode = operateurRepository.findTopByOrderByCodeCptableDesc()
                .map(Operateur::getCode)
                .orElse("0000");

        int next = Integer.parseInt(lastCode) + 1;
        if (next > 9999) {
            throw new RuntimeException("Code limit reached (max 9999)");
        }
        return next;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseOperateurDTO addOperateur(CreateOperatuerDTO createOperatuerDTO) throws NotFoundVilleException, NotFoundProfileException {
        Profile profile =profileRepository.findById(createOperatuerDTO.profil())
                .orElseThrow(() -> new NotFoundProfileException("not found Profile"));
        Operateur operateur = operateurMapper.fromCreate(createOperatuerDTO);
        String profileFirstLettre = String.valueOf(profile.getNom().trim().charAt(0));
        operateur.setCodeCptable(generateNextCode());
        operateur.setCode(profileFirstLettre + generateNextCode());
        Operateur savedOperateur = operateurRepository.save(operateur);
        return operateurMapper.toResponse(savedOperateur);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseOperateurDTO updateOperateur(Long id, CreateOperatuerDTO createOperatuerDTO) throws NotFoundOperateurException, NotFoundVilleException, NotFoundProfileException {
        Operateur operateur = operateurRepository.findById(id)
                .orElseThrow(() -> new NotFoundOperateurException("not found Operateur"));
        Operateur updatOperateur = operateurMapper.fromCreate(createOperatuerDTO) ;
        updatOperateur.setId_operateur(operateur.getId_operateur());
        Operateur savedOperateur = operateurRepository.save(updatOperateur);
        return operateurMapper.toResponse(savedOperateur);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteOperateur(Long id) throws NotFoundOperateurException {
        Operateur operateur = operateurRepository.findById(id)
                .orElseThrow(() -> new NotFoundOperateurException("not found Operateur"));
        operateurRepository.delete(operateur);
    }

    @Override
    public ResponseOperateurDTO getOperateur(Long id) throws NotFoundOperateurException {
        Operateur operateur = operateurRepository.findById(id)
                .orElseThrow(() -> new NotFoundOperateurException("not found Operateur"));
        return operateurMapper.toResponse(operateur);
    }

    @Override
    public ResponseOperateurDTO getOperateurByCode(Long code) throws NotFoundOperateurException {
        Operateur operateur = operateurRepository.findByCodeCptable(code);
        if(operateur==null) throw new NotFoundOperateurException("not found Operateur");
        return operateurMapper.toResponse(operateur);
    }

    @Override
    public List<ResponseOperateurDTO> getOperateurs() {
        List<Operateur> operateurs = operateurRepository.findAll();
        return operateurs.stream().map(operateurMapper::toResponse).collect(Collectors.toList());
    }



}
