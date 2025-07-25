package ma.ensa.test_stage_projet.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateVilleDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseVilleDTO;
import ma.ensa.test_stage_projet.entities.ServiceExterieur;
import ma.ensa.test_stage_projet.entities.Ville;
import ma.ensa.test_stage_projet.exceptions.NotFoundSEException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;
import ma.ensa.test_stage_projet.mappers.ServiceExterieurMapper;
import ma.ensa.test_stage_projet.mappers.VilleMapper;
import ma.ensa.test_stage_projet.repositories.ServiceExterieurRepository;
import ma.ensa.test_stage_projet.repositories.VilleRepositiry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final ServiceExterieurMapper serviceExterieurMapper;
    @Transactional(rollbackFor = NotFoundSEException.class)
    @Override
    public ResponseVilleDTO saveVille(CreateVilleDTO villeDTO){
        Ville ville = villeMapper.fromCreate(villeDTO);
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
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseVilleDTO updateVille(CreateVilleDTO villeDTO, Long id) throws NotFoundVilleException, NotFoundSEException {
        villeRepositiry.findById(id).orElseThrow(()->new NotFoundVilleException("Ville not found"));
        Ville ville = villeMapper.fromCreate(villeDTO);
        ville.setId_ville(id);
        Ville savedVille =villeRepositiry.save(ville);
        return villeMapper.toResponse(savedVille);
    }
    @Override
    public ResponseVilleDTO getVilleDTO(Long id) throws NotFoundVilleException {
        Ville ville = villeRepositiry.findById(id).orElseThrow(()->new NotFoundVilleException("Ville not found"));
        ResponseVilleDTO villeDTO = villeMapper.toResponse(ville);
        return villeDTO;
    }
    @Override
    public ResponseVilleDTO getVilleByName(String nom) throws NotFoundVilleException {
        Ville ville = villeRepositiry.findByDesignation(nom);
        if(ville == null) throw new NotFoundVilleException("Ville not found");
        ResponseVilleDTO villeDTO = villeMapper.toResponse(ville);
        return villeDTO;
    }

    @Transactional(rollbackFor = Exception.class)
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
    public ResponseVilleDTO getVilleByCode(String code) throws NotFoundVilleException {
        Ville ville = villeRepositiry.findByCode(code);
        return villeMapper.toResponse(ville);
    }


}
