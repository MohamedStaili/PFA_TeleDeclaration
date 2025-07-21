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
import ma.ensa.test_stage_projet.repositories.VilleRepositiry;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VilleSeServiceImpl implements VilleSeService {
    private final VilleRepositiry villeRepositiry;
    private final ServiceExterieurRepository serviceExterieurRepository;
    private final VilleMapper villeMapper;
    private final ServiceExterieurMapper serviceExterieurMapper;
    @Override
    public VilleDTO saveVille(VilleDTO villeDTO, String nom_se) throws NotFoundSEException {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findByNomSE(nom_se);
        if (serviceExterieur == null) throw new NotFoundSEException("Service Exterieur not Found");
        villeDTO.setId_se(serviceExterieur.getId_se());
        Ville ville = villeMapper.toVille(villeDTO);
         Ville savedVille =villeRepositiry.save(ville);
         return villeMapper.toVilleDto(savedVille);
    }

    @Override
    public ServiceExterieurDTO saveExterieur(ServiceExterieurDTO serviceExterieurDTO, VilleDTO adresseDTO, List<VilleDTO> villes) throws NotFoundSEException, NotFoundVilleException {
        serviceExterieurDTO.setVille_id(adresseDTO.getId());
        Ville adresse = villeMapper.toVille(adresseDTO);
        villeRepositiry.save(adresse);
        ServiceExterieur serviceExterieur= serviceExterieurMapper.toServiceExterieur(serviceExterieurDTO);
        adresse.setServiceExterieur(serviceExterieur);
        ServiceExterieur savedSE =serviceExterieurRepository.save(serviceExterieur);
        for (VilleDTO villeDTO : villes) {
            villeDTO.setId_se(serviceExterieur.getId_se());
            Ville ville = villeMapper.toVille(villeDTO);
            villeRepositiry.save(ville);
        }
        return serviceExterieurMapper.toServiceExterieurDTO(savedSE);
    }

    @Override
    public void deleteVilleFromSE(String nomSE, String nomVille) throws NotFoundVilleException, NotFoundSEException {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findByNomSE(nomSE);
        if(serviceExterieur == null) throw new NotFoundSEException("Service Exterieur not Found");
        Ville ville = villeRepositiry.findByDesignation(nomVille);
        if(ville==null) throw new NotFoundVilleException("ville not found");

        serviceExterieur.getVilles().remove(ville);
        villeRepositiry.save(ville);
        serviceExterieurRepository.save(serviceExterieur);

    }
    @Override
    public List<VilleDTO> getVilles() {
        List<Ville> villes = villeRepositiry.findAll();
        List<VilleDTO> villeDTOs = new ArrayList<>();
        villeDTOs =villes.stream().map(villeMapper::toVilleDto).toList();
        return villeDTOs;
    }
    @Override
    public List<VilleDTO> getServiceExterieurVilles(String nomSE) throws NotFoundSEException {
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
    public ServiceExterieurDTO updateSE(ServiceExterieurDTO serviceExterieurDTO, Long id) throws NotFoundSEException, NotFoundVilleException {
        serviceExterieurRepository.findById(id).orElseThrow(()->new NotFoundSEException("Service Exterieur not found"));
        serviceExterieurDTO.setId_se(id);
        ServiceExterieur serviceExterieur = serviceExterieurMapper.toServiceExterieur(serviceExterieurDTO);
        ServiceExterieur savedSE = serviceExterieurRepository.save(serviceExterieur);
        return serviceExterieurMapper.toServiceExterieurDTO(savedSE);

    }
    @Override
    public VilleDTO updateVille(VilleDTO villeDTO, Long id) throws NotFoundVilleException, NotFoundSEException {
        villeRepositiry.findById(id).orElseThrow(()->new NotFoundVilleException("Ville not found"));
        villeDTO.setId(id);
        Ville ville = villeMapper.toVille(villeDTO);
        Ville savedVille =villeRepositiry.save(ville);
        return villeMapper.toVilleDto(savedVille);
    }
    @Override
    public List<ServiceExterieurDTO> getServiceExterieurDTOs() {
        List<ServiceExterieurDTO> serviceExterieurDTOs = new ArrayList<>();
        List<ServiceExterieur> serviceExterieurs = serviceExterieurRepository.findAll();
        serviceExterieurDTOs = serviceExterieurs.stream().map(serviceExterieurMapper::toServiceExterieurDTO).toList();
        return serviceExterieurDTOs;
    }
    @Override
    public VilleDTO getVilleDTO(Long id) throws NotFoundVilleException {
        Ville ville = villeRepositiry.findById(id).orElseThrow(()->new NotFoundVilleException("Ville not found"));
        VilleDTO villeDTO = villeMapper.toVilleDto(ville);
        return villeDTO;
    }
    @Override
    public VilleDTO getVilleBtName(String nom) throws NotFoundVilleException {
        Ville ville = villeRepositiry.findByDesignation(nom);
        if(ville == null) throw new NotFoundVilleException("Ville not found");
        VilleDTO villeDTO = villeMapper.toVilleDto(ville);
        return villeDTO;
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
    public void deleteVille(Long id) throws NotFoundVilleException {
        Ville ville = villeRepositiry.findById(id).orElseThrow(()->new NotFoundVilleException("Ville not found"));
        villeRepositiry.delete(ville);
    }
    public void deleteServiceExterieur(Long id) throws NotFoundSEException {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findById(id).orElseThrow(()->new NotFoundSEException("service exterieur not found"));
        serviceExterieurRepository.delete(serviceExterieur);
    }

}
