package ma.ensa.test_stage_projet.mappers;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreateRegimeDTO;
import ma.ensa.test_stage_projet.Dtos.ResponseRegimeDTO;
import ma.ensa.test_stage_projet.entities.RegimeImportation;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegimeMapper {

    public RegimeImportation fromCreate(CreateRegimeDTO createRegimeDTO) {
        RegimeImportation regimeImportation = new RegimeImportation();
        regimeImportation.setCode(createRegimeDTO.code());
        regimeImportation.setDesignation(createRegimeDTO.designation());
        return regimeImportation;
    }
    public ResponseRegimeDTO toResponse(RegimeImportation regimeImportation) {
        return new ResponseRegimeDTO(
                regimeImportation.getId_regime_impor(),
                regimeImportation.getCode(),
                regimeImportation.getDesignation()
        );
    }
}
