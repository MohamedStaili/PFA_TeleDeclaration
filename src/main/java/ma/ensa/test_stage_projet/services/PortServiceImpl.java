package ma.ensa.test_stage_projet.services;

import lombok.RequiredArgsConstructor;
import ma.ensa.test_stage_projet.Dtos.CreatePortDTO;
import ma.ensa.test_stage_projet.Dtos.ResponsePortDTO;
import ma.ensa.test_stage_projet.entities.PortDechargemnt;
import ma.ensa.test_stage_projet.exceptions.NotFoundPortException;
import ma.ensa.test_stage_projet.exceptions.NotFoundSEException;
import ma.ensa.test_stage_projet.exceptions.NotFoundVilleException;
import ma.ensa.test_stage_projet.mappers.PortMapper;
import ma.ensa.test_stage_projet.repositories.PortDechargementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PortServiceImpl implements PortService {
    private final PortDechargementRepository portDechargementRepository;
    private final PortMapper portMapper;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponsePortDTO addPort(CreatePortDTO createPortDTO) throws NotFoundSEException, NotFoundVilleException {
        PortDechargemnt portDechargemnt = portMapper.fromCreate(createPortDTO);
        PortDechargemnt portDechargemnt1 = portDechargementRepository.save(portDechargemnt);
        return portMapper.toResponse(portDechargemnt1);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponsePortDTO updatePort(CreatePortDTO createPortDTO , Long idPort) throws NotFoundPortException, NotFoundSEException, NotFoundVilleException {
        portDechargementRepository
                .findById(idPort).orElseThrow(() -> new NotFoundPortException("port not found"));
        PortDechargemnt portDechargemnt1 = portMapper.fromCreate(createPortDTO);
        portDechargemnt1.setId_port(idPort);
        PortDechargemnt savedPort =portDechargementRepository.save(portDechargemnt1);
        return portMapper.toResponse(savedPort);
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletePort(Long idPort) throws NotFoundPortException {
        PortDechargemnt portDechargemnt = portDechargementRepository
                .findById(idPort).orElseThrow(() -> new NotFoundPortException("port not found"));
        portDechargementRepository.delete(portDechargemnt);
    }

    @Override
    public ResponsePortDTO getPort(Long idPort) throws NotFoundPortException {
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
