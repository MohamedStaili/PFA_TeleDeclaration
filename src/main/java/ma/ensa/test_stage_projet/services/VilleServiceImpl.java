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
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VilleServiceImpl implements VilleService {
    private final VilleRepositiry villeRepositiry;
    private final ServiceExterieurRepository serviceExterieurRepository;
    private final VilleMapper villeMapper;
    private final ServiceExterieurMapper serviceExterieurMapper;
    @Override
    public VilleDTO saveVille(VilleDTO villeDTO, String nom_se) throws NotFoundSEException {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findByNomSE(nom_se);
        if (serviceExterieur == null) throw new NotFoundSEException("Service Exterieur not Found");
        VilleDTO villeDTO1 = new VilleDTO(
                villeDTO.id(),
                villeDTO.designation(),
                villeDTO.code(),
                serviceExterieur.getId_se()
        );

        Ville ville = villeMapper.toVille(villeDTO1);
         Ville savedVille =villeRepositiry.save(ville);
         return villeMapper.toVilleDto(savedVille);
    }

    @Override
    public ServiceExterieurDTO saveExterieur(ServiceExterieurDTO serviceExterieurDTO, VilleDTO adresseDTO, List<VilleDTO> villes) throws NotFoundSEException, NotFoundVilleException {
        ServiceExterieurDTO dto = new ServiceExterieurDTO(
                serviceExterieurDTO.id_se(),
                serviceExterieurDTO.nomSE(),
                serviceExterieurDTO.code(),
                adresseDTO.id()
        );

        Ville adresse = villeMapper.toVille(adresseDTO);
        villeRepositiry.save(adresse);
        ServiceExterieur serviceExterieur= serviceExterieurMapper.toServiceExterieur(dto);
        adresse.setServiceExterieur(serviceExterieur);
        ServiceExterieur savedSE =serviceExterieurRepository.save(serviceExterieur);
        for (VilleDTO villeDTO : villes) {
            VilleDTO Villedto = new VilleDTO(
                    villeDTO.id(),
                    villeDTO.designation(),
                    villeDTO.code(),
                    serviceExterieur.getId_se()
            );
            Ville ville = villeMapper.toVille(Villedto);
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
    public List<VilleDTO> getVilles(int page , int size) {
        List<Ville> villes = villeRepositiry.findAll(PageRequest.of(page,size)).getContent();
        return villes.stream().map(villeMapper::toVilleDto).toList();
    }
    @Override
    public VilleDTO updateVille(VilleDTO villeDTO, Long id) throws NotFoundVilleException, NotFoundSEException {
        villeRepositiry.findById(id).orElseThrow(()->new NotFoundVilleException("Ville not found"));
        VilleDTO dto = new VilleDTO(
                id,
                villeDTO.designation(),
                villeDTO.code(),
                villeDTO.idSE()
        );
        Ville ville = villeMapper.toVille(dto);
        Ville savedVille =villeRepositiry.save(ville);
        return villeMapper.toVilleDto(savedVille);
    }
    @Override
    public VilleDTO getVilleDTO(Long id) throws NotFoundVilleException {
        Ville ville = villeRepositiry.findById(id).orElseThrow(()->new NotFoundVilleException("Ville not found"));
        VilleDTO villeDTO = villeMapper.toVilleDto(ville);
        return villeDTO;
    }
    @Override
    public VilleDTO getVilleByName(String nom) throws NotFoundVilleException {
        Ville ville = villeRepositiry.findByDesignation(nom);
        if(ville == null) throw new NotFoundVilleException("Ville not found");
        VilleDTO villeDTO = villeMapper.toVilleDto(ville);
        return villeDTO;
    }

    @Override
    public void deleteVille(Long id) throws NotFoundVilleException {
        Ville ville = villeRepositiry.findById(id).orElseThrow(()->new NotFoundVilleException("Ville not found"));
        villeRepositiry.delete(ville);
    }

    @Override
    public VilleDTO getVilleByCode(String code) throws NotFoundVilleException {
        Ville ville = villeRepositiry.findByCode(code);
        return villeMapper.toVilleDto(ville);
    }


    public void deleteServiceExterieur(Long id) throws NotFoundSEException {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findById(id).orElseThrow(()->new NotFoundSEException("service exterieur not found"));
        serviceExterieurRepository.delete(serviceExterieur);
    }

}
