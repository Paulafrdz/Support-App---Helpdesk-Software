package dev.paula.api_helpdesk_software.technician;

import java.util.List;
import java.util.stream.Collectors;

import dev.paula.api_helpdesk_software.exceptions.TechnicianNotFoundExceptions;
import dev.paula.api_helpdesk_software.implementation.ITechnicianService;

public class TechnicianServiceImpl implements  ITechnicianService<TechnicianDTOResponse, TechnicianDTORequest>{
    
    private final TechnicianRepository repository;

    public TechnicianServiceImpl(TechnicianRepository repository) {
        this.repository = repository;
    }

    @Override
    public TechnicianDTOResponse storeEntity(TechnicianDTORequest dto){
        TechnicianEntity entity = TechnicianMapper.toEntity(dto);
        TechnicianEntity saved = repository.save(entity);

        return TechnicianMapper.toDTO(saved);
    }

    @Override
    public List<TechnicianDTOResponse> getEntities() {
        return repository.findAll()
                        .stream()
                        .map(TechnicianMapper::toDTO)
                        .collect(Collectors.toList());
    }

    @Override
    public TechnicianDTOResponse getEntityById(Long id) {
        TechnicianEntity technician = repository.findById(id)
            .orElseThrow(() -> new TechnicianNotFoundExceptions("No encontrado el técnico con id: " + id));
        return TechnicianMapper.toDTO(technician);
    }
}
