package ma.ensa.test_stage_projet.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateVilleDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseVilleDTO;
import ma.ensa.test_stage_projet.entities.ServiceExterieur;
import ma.ensa.test_stage_projet.entities.Ville;
import ma.ensa.test_stage_projet.exceptions.DuplicateCodeException;
import ma.ensa.test_stage_projet.exceptions.DuplicateDesignationException;
import ma.ensa.test_stage_projet.exceptions.NotFoundSEException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;
import ma.ensa.test_stage_projet.mappers.ServiceExterieurMapper;
import ma.ensa.test_stage_projet.mappers.VilleMapper;
import ma.ensa.test_stage_projet.repositories.ServiceExterieurRepository;
import ma.ensa.test_stage_projet.repositories.VilleRepositiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class VilleServiceImpl implements VilleService {
    private final VilleRepositiry villeRepositiry;
    private final ServiceExterieurRepository serviceExterieurRepository;
    private final VilleMapper villeMapper;
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public ResponseVilleDTO saveVille(CreateVilleDTO villeDTO){
        Ville ville = villeMapper.fromCreate(villeDTO);
        if(villeRepositiry.findByDesignation(villeDTO.designation()) !=null)
            throw new DuplicateDesignationException("this designation is already in use");
        if(villeRepositiry.findByCode(villeDTO.code()) !=null) throw new DuplicateCodeException("this code is already in use");

        Ville savedVille = villeRepositiry.save(ville);
         return villeMapper.toResponse(savedVille);
    }
    @Override
    public Map<String,Object> getVilles(int page , int size) {
        Page<Ville> villePage = villeRepositiry.findAll(PageRequest.of(page,size));
        List<Ville> villes = villePage.getContent();

        List<ResponseVilleDTO> villeDTOS =villes.stream().map(villeMapper::toResponse).toList();
        Map<String,Object> response = new HashMap<>();
        response.put("villes",villeDTOS);
        response.put("currentPage",villePage.getNumber());
        response.put("totalPages",villePage.getTotalPages());
        response.put("totalElements",villePage.getTotalElements());
        return response;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public ResponseVilleDTO updateVille(CreateVilleDTO villeDTO, Long id) {
        Ville villeValidate = villeRepositiry.findById(id).orElseThrow(()->new NotFoundVilleException("Ville not found"));
        Ville villeValidate2 = villeRepositiry.findByDesignation(villeDTO.designation());
        Ville villeValidate3 = villeRepositiry.findByCode(villeDTO.code());
        if(villeValidate != villeValidate2)
            throw new DuplicateDesignationException("this designation is already in use");

        if(villeValidate != villeValidate3)
            throw new DuplicateCodeException("this code is already in use");

        Ville ville = villeMapper.fromCreate(villeDTO);
        ville.setId_ville(id);
        Ville savedVille =villeRepositiry.save(ville);
        return villeMapper.toResponse(savedVille);
    }
    @Override
    public ResponseVilleDTO getVilleDTO(Long id)  {
        Ville ville = villeRepositiry.findById(id).orElseThrow(()->new NotFoundVilleException("Ville not found"));
        return villeMapper.toResponse(ville);

    }
    @Override
    public ResponseVilleDTO getVilleByName(String nom) {
        Ville ville = villeRepositiry.findByDesignation(nom);
        if(ville == null) throw new NotFoundVilleException("Ville not found");
        return villeMapper.toResponse(ville);

    }
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public void deleteVille(Long id) throws NotFoundVilleException {
        Ville ville = villeRepositiry.findById(id).orElseThrow(()->new NotFoundVilleException("Ville not found"));
        if(ville.getEstAdresseSE()){
            ServiceExterieur serviceExterieur = ville.getServiceExterieur();
            serviceExterieur.setAdresseVilleId(null);
            serviceExterieurRepository.save(serviceExterieur);
        }
        villeRepositiry.delete(ville);
    }

    @Override
    public ResponseVilleDTO getVilleByCode(String code)  {
        Ville ville = villeRepositiry.findByCode(code);
        return villeMapper.toResponse(ville);
    }


}
