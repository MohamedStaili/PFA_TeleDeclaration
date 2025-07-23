package ma.ensa.test_stage_projet.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateServiceExterieurDTO;
import ma.ensa.test_stage_projet.Dtos.CreateVilleDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseServiceExterieurDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseVilleDTO;
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
@RequiredArgsConstructor
public class ServiceExterieurServiceImpl implements ServiceExterieurService {
    ServiceExterieurRepository serviceExterieurRepository;
    VilleRepositiry villeRepositiry ;
    ServiceExterieurMapper serviceExterieurMapper;
    VilleMapper villeMapper;
    @Transactional(rollbackFor = {NotFoundVilleException.class, NotFoundSEException.class})
    @Override
    public ResponseServiceExterieurDTO saveExterieur(CreateServiceExterieurDTO serviceExterieurDTO, CreateVilleDTO adresseDTO, List<CreateVilleDTO> villes) throws NotFoundSEException, NotFoundVilleException {

        Ville adresse = villeMapper.fromCreate(adresseDTO);
        ServiceExterieur serviceExterieur= serviceExterieurMapper.fromCreate(serviceExterieurDTO);
        //villeRepositiry.save(adresse);
        adresse.setServiceExterieur(serviceExterieur);
        serviceExterieur.setAdresse(adresse);
//        adresse.setServiceExterieur(serviceExterieur);
//        ServiceExterieur savedSE =serviceExterieurRepository.save(serviceExterieur);
        for (CreateVilleDTO villeDTO : villes) {
            CreateVilleDTO Villedto = new CreateVilleDTO(
                    villeDTO.designation(),
                    villeDTO.code(),
                    serviceExterieur.getNomSE()
            );
            Ville ville = villeMapper.fromCreate(Villedto);
            serviceExterieur.getVilles().add(ville);

        }
        ServiceExterieur savedSE=serviceExterieurRepository.save(serviceExterieur);
        return serviceExterieurMapper.toResponse(savedSE);
    }
    @Override
    public List<ResponseVilleDTO> getServiceExterieurVilles(String nomSE, int page, int size) throws NotFoundSEException {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findByNomSE(nomSE);
        if(serviceExterieur == null) throw new NotFoundSEException("Service Exterieur not Found");
        List<Ville> villes = serviceExterieur.getVilles();
        List<ResponseVilleDTO> villeDTOs = villes.stream().map(villeMapper::toResponse).toList();
        return villeDTOs;
    }
    @Override
    public ResponseVilleDTO getAddresseSE(String nomSE) throws NotFoundVilleException, NotFoundSEException {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findByNomSE(nomSE);
        if(serviceExterieur == null) throw new NotFoundSEException("Service Exterieur not Found");
        Ville ville = serviceExterieur.getAdresse();
        return villeMapper.toResponse(ville);
    }
    @Transactional(rollbackFor = {NotFoundSEException.class , NotFoundVilleException.class})
    @Override
    public ResponseServiceExterieurDTO updateSE(CreateServiceExterieurDTO dto, Long id) throws NotFoundSEException, NotFoundVilleException {
        serviceExterieurRepository.findById(id)
                .orElseThrow(() -> new NotFoundSEException("Service Exterieur not found"));

        ServiceExterieur se = serviceExterieurMapper.fromCreate(dto);
        ServiceExterieur savedSE = serviceExterieurRepository.save(se);
        return serviceExterieurMapper.toResponse(savedSE);
    }

    @Override
    public List<ResponseServiceExterieurDTO> getServiceExterieurDTOs() {
        List<ResponseServiceExterieurDTO> serviceExterieurDTOs;
        List<ServiceExterieur> serviceExterieurs = serviceExterieurRepository.findAll();
        serviceExterieurDTOs = serviceExterieurs.stream().map(serviceExterieurMapper::toResponse).toList();
        return serviceExterieurDTOs;
    }
    @Override
    public ResponseServiceExterieurDTO getServiceExterieurDTO(Long id) throws NotFoundSEException {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findById(id)
                .orElseThrow(()-> new NotFoundSEException("service exterieur not found"));
        return serviceExterieurMapper.toResponse(serviceExterieur);
    }

    @Override
    public ResponseServiceExterieurDTO getServiceExterieurByName(String nom) throws NotFoundSEException {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findByNomSE(nom);
        if(serviceExterieur == null) throw new NotFoundSEException("service exterieur not found");
        return serviceExterieurMapper.toResponse(serviceExterieur);
    }
    @Override
    public ResponseServiceExterieurDTO getServiceExterieurDTOByCode(String code) throws NotFoundSEException {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findByCodeSE(code);
        return serviceExterieurMapper.toResponse(serviceExterieur);
    }
    @Transactional(rollbackFor = NotFoundSEException.class)
    @Override
    public void deleteServiceExterieur(Long id) throws NotFoundSEException {
        ServiceExterieur serviceExterieur = serviceExterieurRepository.findById(id).orElseThrow(()->new NotFoundSEException("service exterieur not found"));
        serviceExterieurRepository.delete(serviceExterieur);
    }
}
