package ma.ensa.test_stage_projet.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.ServiceExterieurDTO;
import ma.ensa.test_stage_projet.Dtos.VilleDTO;
import ma.ensa.test_stage_projet.entities.ServiceExterieur;
import ma.ensa.test_stage_projet.entities.Ville;
import ma.ensa.test_stage_projet.exceptions.NotFoundSEException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;
import ma.ensa.test_stage_projet.mappers.ServiceExterieurMapper;
import ma.ensa.test_stage_projet.mappers.VilleMapper;
import ma.ensa.test_stage_projet.repositories.ServiceExterieurRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class ServiceExterieurServiceImpl implements ServiceExterieurService {
    ServiceExterieurRepository serviceExterieurRepository;
    ServiceExterieurMapper serviceExterieurMapper;
    VilleMapper villeMapper;
    @Override
    public List<VilleDTO> getServiceExterieurVilles(String nomSE, int page,int size) throws NotFoundSEException {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findByNomSE(nomSE);
        if(serviceExterieur == null) throw new NotFoundSEException("Service Exterieur not Found");
        List<Ville> villes = serviceExterieur.getVilles();
        List<VilleDTO> villeDTOs = new ArrayList<>();
        villeDTOs = villes.stream().map(villeMapper::toVilleDto).toList();
        return villeDTOs;
    }
    @Override
    public VilleDTO getAddresseSE(String nomSE) throws NotFoundVilleException, NotFoundSEException {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findByNomSE(nomSE);
        if(serviceExterieur == null) throw new NotFoundSEException("Service Exterieur not Found");
        Ville ville = serviceExterieur.getAdresse();
        return villeMapper.toVilleDto(ville);
    }
    @Override
    public ServiceExterieurDTO updateSE(ServiceExterieurDTO dto, Long id) throws NotFoundSEException, NotFoundVilleException {
        serviceExterieurRepository.findById(id)
                .orElseThrow(() -> new NotFoundSEException("Service Exterieur not found"));

        ServiceExterieurDTO updatedDto = new ServiceExterieurDTO(
                id,
                dto.nomSE(),
                dto.code(),
                dto.ville_id()
        );

        ServiceExterieur se = serviceExterieurMapper.toServiceExterieur(updatedDto);
        ServiceExterieur savedSE = serviceExterieurRepository.save(se);
        return serviceExterieurMapper.toServiceExterieurDTO(savedSE);
    }

    @Override
    public List<ServiceExterieurDTO> getServiceExterieurDTOs() {
        List<ServiceExterieurDTO> serviceExterieurDTOs = new ArrayList<>();
        List<ServiceExterieur> serviceExterieurs = serviceExterieurRepository.findAll();
        serviceExterieurDTOs = serviceExterieurs.stream().map(serviceExterieurMapper::toServiceExterieurDTO).toList();
        return serviceExterieurDTOs;
    }
    @Override
    public ServiceExterieurDTO getServiceExterieurDTO(Long id) throws NotFoundSEException {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findById(id)
                .orElseThrow(()-> new NotFoundSEException("service exterieur not found"));
        return serviceExterieurMapper.toServiceExterieurDTO(serviceExterieur);
    }

    @Override
    public ServiceExterieurDTO getServiceExterieurByName(String nom) throws NotFoundSEException {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findByNomSE(nom);
        if(serviceExterieur == null) throw new NotFoundSEException("service exterieur not found");
        return serviceExterieurMapper.toServiceExterieurDTO(serviceExterieur);
    }
    @Override
    public ServiceExterieurDTO getServiceExterieurDTOByCode(String code) throws NotFoundSEException {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findByCodeSE(code);
        return serviceExterieurMapper.toServiceExterieurDTO(serviceExterieur);
    }
}
