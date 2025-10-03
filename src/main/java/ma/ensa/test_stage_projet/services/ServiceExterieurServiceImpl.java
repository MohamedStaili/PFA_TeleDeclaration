package ma.ensa.test_stage_projet.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateServiceExterieurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseServiceExterieurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseVilleDTO;
import ma.ensa.test_stage_projet.entities.ServiceExterieur;
import ma.ensa.test_stage_projet.entities.Ville;
import ma.ensa.test_stage_projet.exceptions.*;
import ma.ensa.test_stage_projet.mappers.ServiceExterieurMapper;
import ma.ensa.test_stage_projet.mappers.VilleMapper;
import ma.ensa.test_stage_projet.repositories.ServiceExterieurRepository;
import ma.ensa.test_stage_projet.repositories.VilleRepositiry;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceExterieurServiceImpl implements ServiceExterieurService {
    private final ServiceExterieurRepository serviceExterieurRepository;
    private final VilleRepositiry villeRepositiry ;
    private final ServiceExterieurMapper serviceExterieurMapper;
    private final VilleMapper villeMapper;
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public Map<String,Object> saveExterieur(CreateServiceExterieurDTO serviceExterieurDTO)  {
        ServiceExterieur seDesi = serviceExterieurRepository.findByNomSE(serviceExterieurDTO.code()) ;
        if(seDesi != null) throw new DuplicateNomException("Nom already exists");
        ServiceExterieur seCode = serviceExterieurRepository.findByCodeSE(serviceExterieurDTO.code()) ;
        if(seCode != null) throw new DuplicateCodeException("code already exists");
        ServiceExterieur serviceExterieur = serviceExterieurMapper.fromCreate( serviceExterieurDTO );
        ServiceExterieur saved = serviceExterieurRepository.save(serviceExterieur);
        Map<String,Object> response = new HashMap<>();
        response.put("id", saved.getIdSE());
        response.put("nom", saved.getNomSE());
        response.put("code", saved.getCodeSE());
        return response;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public Map<String,Object> addAdresse(Long idSE, Long idVille)  {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findById(idSE)
                .orElseThrow(() -> new NotFoundSEException("Service Exterieur Not Found"));
        Ville ville = villeRepositiry.findById(idVille).orElseThrow(() -> new NotFoundVilleException("Ville Not Found"));
        if(serviceExterieur.getAdresse() ==null){
            serviceExterieur.setAdresseVilleId(idVille);
            if(serviceExterieur.getVilles() !=null && !serviceExterieur.getVilles().contains(ville)) serviceExterieur.addVille(ville);
            ville.setEstAdresseSE(true);
            serviceExterieurRepository.save(serviceExterieur);
            Map<String,Object> response = new HashMap<>();
            response.put("service exterieur", serviceExterieurMapper.toResponse(serviceExterieur));
            response.put("addresse ajoute avec succés", villeMapper.toResponse(ville));
            return response;
        }else {
            throw new AddresseAlreadyADD("addresse deja ajoute");
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public Map<String,Object> updateAddresse(Long idSE, Long idVille) {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findById(idSE)
                .orElseThrow(() -> new NotFoundSEException("Service Exterieur Not Found"));
        Ville ville = villeRepositiry.findById(idVille).orElseThrow(() -> new NotFoundVilleException("Ville Not Found"));
        if(serviceExterieur.getVilles()!=null && serviceExterieur.getVilles().contains(ville)){
            Ville ville1 = serviceExterieur.getAdresse();
            ville1.setEstAdresseSE(false);
            serviceExterieur.setAdresseVilleId(idVille);
            serviceExterieur.addVille(ville);
            ville.setEstAdresseSE(true);
            serviceExterieurRepository.save(serviceExterieur);
            Map<String,Object> response = new HashMap<>();
            response.put("service exterieur", serviceExterieurMapper.toResponse(serviceExterieur));
            response.put("nouvelle addresse", villeMapper.toResponse(ville));
            return response;

        }else {
            throw new VilleNotInSEException("ajouter la ville en premier aux villes attches a cette service exterieur");
        }
    }

    @Override
    public List<ResponseVilleDTO> getServiceExterieurVilles(String nomSE, int page, int size)  {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findByNomSE(nomSE);
        if(serviceExterieur == null) throw new NotFoundSEException("Service Exterieur not Found");
        List<Ville> villes = serviceExterieur.getVilles();
        List<ResponseVilleDTO> villeDTOs = villes.stream().map(villeMapper::toResponse).toList();
        return villeDTOs;
    }
    @Override
    public ResponseVilleDTO getAddresseSE(String nomSE)  {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findByNomSE(nomSE);
        if(serviceExterieur == null) throw new NotFoundSEException("Service Exterieur not Found");
        Ville ville = serviceExterieur.getAdresse();
        return villeMapper.toResponse(ville);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public ResponseServiceExterieurDTO updateSE(CreateServiceExterieurDTO dto, Long id)  {
        ServiceExterieur seOld =serviceExterieurRepository.findById(id)
                .orElseThrow(() -> new NotFoundSEException("Service Exterieur not found"));
        ServiceExterieur seNom = serviceExterieurRepository.findByNomSE(dto.nomSE());
        if(seOld != seNom ) throw new DuplicateNomException("Service Exterieur not found");
        ServiceExterieur seCode = serviceExterieurRepository.findByCodeSE(dto.code());
        if(seOld != seCode) throw new DuplicateCodeException("Service Exterieur not found");
        ServiceExterieur se = serviceExterieurMapper.fromCreate(dto);
        ServiceExterieur savedSE = serviceExterieurRepository.save(se);
        return serviceExterieurMapper.toResponse(savedSE);
    }

    @Override
    public List<Map<String, Object>> getServiceExterieurDTOs() {
        List<ServiceExterieur> serviceExterieurs = serviceExterieurRepository.findAll();

        return serviceExterieurs.stream().map((this::getStringObjectMap)).collect(Collectors.toList());
    }
    @Override
    public Map<String,Object> getServiceExterieurDTO(Long id)  {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findById(id)
                .orElseThrow(()-> new NotFoundSEException("service exterieur not found"));
        return getStringObjectMap(serviceExterieur);
    }

    private Map<String, Object> getStringObjectMap(ServiceExterieur serviceExterieur) {
        Map<String,Object> serviceExterieurDTO = new HashMap<>();
        List<ResponseVilleDTO> villeDTOS = serviceExterieur.getVilles().stream().map(villeMapper::toResponse).toList();
        serviceExterieurDTO.put("Service Exterieur",serviceExterieurMapper.toResponse(serviceExterieur));
        if(serviceExterieur.getAdresse() != null) serviceExterieurDTO.put("addresse",villeMapper.toResponse(serviceExterieur.getAdresse()));
        serviceExterieurDTO.put("villes attaches",villeDTOS);
        return serviceExterieurDTO;
    }

    @Override
    public Map<String, Object> getServiceExterieurByName(String nom)  {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findByNomSE(nom);
        if(serviceExterieur == null) throw new NotFoundSEException("service exterieur not found");
        return getStringObjectMap(serviceExterieur);
    }
    @Override
    public Map<String, Object> getServiceExterieurDTOByCode(String code)  {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findByCodeSE(code);
        if(serviceExterieur == null) throw new NotFoundSEException("service exterieur not found");
        return getStringObjectMap(serviceExterieur);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public void deleteServiceExterieur(Long id)  {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findById(id).orElseThrow(()->new NotFoundSEException("service exterieur not found"));
        serviceExterieurRepository.delete(serviceExterieur);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public ResponseVilleDTO addVilleToSE(Long idSE, Long idVille) {
        Ville ville = villeRepositiry.findById(idVille).orElseThrow(() -> new NotFoundVilleException("Ville not Exist"));
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findById(idSE)
                .orElseThrow(() -> new NotFoundSEException("service exterieur not exist"));
        serviceExterieur.addVille(ville);
        return villeMapper.toResponse(ville);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public ResponseVilleDTO deleteVilleFromSE(Long idSE, Long idVille) {
        Ville ville = villeRepositiry.findById(idVille).orElseThrow(() -> new NotFoundVilleException("Ville not Exist"));
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findById(idSE)
                .orElseThrow(() -> new NotFoundSEException("service exterieur not exist"));
        serviceExterieur.removeVille(ville);
        return villeMapper.toResponse(ville);
    }

//    @Transactional(rollbackFor = {NotFoundSEException.class, NotFoundVilleException.class})
//    @Override
//    public void deleteVilleFromSE(Long idSE, Long idVille) throws NotFoundVilleException, NotFoundSEException {
//        ServiceExterieur serviceExterieur = serviceExterieurRepository.findById(idSE)
//                .orElseThrow(()->new NotFoundSEException("service exterieur not found"));
//        Ville ville = villeRepositiry.findById(idVille).orElseThrow(()->new NotFoundVilleException("ville not found"));
//
//        serviceExterieur.getVilles().remove(ville);
//        ville.setServiceExterieur(null);
//        serviceExterieurRepository.save(serviceExterieur);
//        villeRepositiry.save(ville);
//    }
}
