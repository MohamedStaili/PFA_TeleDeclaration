package ma.ensa.test_stage_projet.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreatePortDTO;
import ma.ensa.test_stage_projet.Dtos.ResponsePortDTO;
import ma.ensa.test_stage_projet.entities.PortDechargemnt;
import ma.ensa.test_stage_projet.exceptions.*;
import ma.ensa.test_stage_projet.mappers.PortMapper;
import ma.ensa.test_stage_projet.repositories.PortDechargementRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PortServiceImpl implements PortService {
    private final PortDechargementRepository portDechargementRepository;
    private final PortMapper portMapper;
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public ResponsePortDTO addPort(CreatePortDTO createPortDTO)  {
        PortDechargemnt portCode = portDechargementRepository.findByCode(createPortDTO.code());
        PortDechargemnt portDesi = portDechargementRepository.findByDesignation(createPortDTO.designation());
        if(portCode != null) throw new DuplicateCodeException("code already in use");
        if(portDesi != null) throw new DuplicateDesignationException("designation not in use");
        PortDechargemnt portDechargemnt = portMapper.fromCreate(createPortDTO);
        PortDechargemnt portDechargemnt1 = portDechargementRepository.save(portDechargemnt);
        return portMapper.toResponse(portDechargemnt1);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public ResponsePortDTO updatePort(CreatePortDTO createPortDTO , Long idPort) {
        PortDechargemnt portOld =portDechargementRepository
                .findById(idPort).orElseThrow(() -> new NotFoundPortException("port not found"));
        PortDechargemnt portCode = portDechargementRepository.findByCode(createPortDTO.code());
        PortDechargemnt portDesi = portDechargementRepository.findByDesignation(createPortDTO.designation());
        if(portOld != portCode) throw new DuplicateCodeException("code already in use");
        if(portOld != portDesi) throw new DuplicateDesignationException("designation not in use");
        PortDechargemnt portDechargemnt1 = portMapper.fromCreate(createPortDTO);
        portDechargemnt1.setId_port(idPort);
        PortDechargemnt savedPort =portDechargementRepository.save(portDechargemnt1);
        return portMapper.toResponse(savedPort);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @Override
    public void deletePort(Long idPort)  {
        PortDechargemnt portDechargemnt = portDechargementRepository
                .findById(idPort).orElseThrow(() -> new NotFoundPortException("port not found"));
        portDechargementRepository.delete(portDechargemnt);
    }

    @Override
    public ResponsePortDTO getPort(Long idPort)  {
        PortDechargemnt portDechargemnt = portDechargementRepository
                .findById(idPort).orElseThrow(() -> new NotFoundPortException("port not found"));

        return portMapper.toResponse(portDechargemnt);
    }

    @Override
    public List<ResponsePortDTO> getPorts() {
        List<PortDechargemnt> portDechargemnts = portDechargementRepository.findAll();

        return portDechargemnts.stream().map(portMapper::toResponse).collect(Collectors.toList());
    }
}
