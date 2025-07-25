package ma.ensa.test_stage_projet.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateRegimeDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseRegimeDTO;
import ma.ensa.test_stage_projet.entities.RegimeImportation;
import ma.ensa.test_stage_projet.exceptions.NotFoundRegimeException;
import ma.ensa.test_stage_projet.mappers.RegimeMapper;
import ma.ensa.test_stage_projet.repositories.RegimeImportationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegimeServiceImpl implements RegimeService {
    private final RegimeImportationRepository regimeImportationRepository;
    private final RegimeMapper regimeMapper;
    @Override
    public ResponseRegimeDTO findByDesignation(String designation) throws NotFoundRegimeException {
        RegimeImportation regimeImportation =
                regimeImportationRepository.findByDesignation(designation);
        if (regimeImportation == null) throw new NotFoundRegimeException("Regime d'importation not found");

        return regimeMapper.toResponse(regimeImportation);
    }

    @Override
    public ResponseRegimeDTO findByCode(String code) throws NotFoundRegimeException {
        RegimeImportation regimeImportation =
                regimeImportationRepository.findByCode(code);
        if (regimeImportation == null) throw new NotFoundRegimeException("Regime d'importation not found");

        return regimeMapper.toResponse(regimeImportation);
    }

    @Override
    public ResponseRegimeDTO findById(Long id) throws NotFoundRegimeException {

        RegimeImportation regimeImportation =
                regimeImportationRepository.findById(id)
                        .orElseThrow(() -> new NotFoundRegimeException("Regime d'importation not found"));
        return regimeMapper.toResponse(regimeImportation);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseRegimeDTO addRegimeImportation(CreateRegimeDTO createRegimeDTO) {
        RegimeImportation regimeImportation = regimeMapper.fromCreate(createRegimeDTO);
        RegimeImportation savedRegime = regimeImportationRepository.save(regimeImportation);
        return regimeMapper.toResponse(savedRegime);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseRegimeDTO updateRegimeImportation(CreateRegimeDTO createRegimeDTO, Long id) throws NotFoundRegimeException {
        regimeImportationRepository.findById(id).orElseThrow(() -> new NotFoundRegimeException("Regime d'importation not found"));
        RegimeImportation regimeImportation = regimeMapper.fromCreate(createRegimeDTO);
        regimeImportation.setId_regime_impor(id);
        RegimeImportation savedRegime = regimeImportationRepository.save(regimeImportation);
        return regimeMapper.toResponse(savedRegime);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRegimeImportation(Long id) throws NotFoundRegimeException {
        regimeImportationRepository.findById(id)
                .orElseThrow(() -> new NotFoundRegimeException("Regime d'importation not found"));
        regimeImportationRepository.deleteById(id);
    }

    @Override
    public List<ResponseRegimeDTO> findAllRegimes() {
        List<ResponseRegimeDTO> responseRegimeDTOS = new ArrayList<>();
        responseRegimeDTOS =
                regimeImportationRepository.findAll().stream().map(regimeMapper::toResponse).toList();
        return responseRegimeDTOS;
    }
}
