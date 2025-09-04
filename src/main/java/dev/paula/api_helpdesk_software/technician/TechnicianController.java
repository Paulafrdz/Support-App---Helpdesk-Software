package dev.paula.api_helpdesk_software.technician;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.paula.api_helpdesk_software.implementation.ITechnicianService;


@RestController
@RequestMapping(path = ("${api-endpoint}/technician"))
public class TechnicianController {
    
    private final ITechnicianService<TechnicianDTOResponse, TechnicianDTORequest> technicianService;

    public TechnicianController(ITechnicianService<TechnicianDTOResponse, TechnicianDTORequest> technicianService) {
        this.technicianService = technicianService;
    }

    @GetMapping("")
    public List<TechnicianDTOResponse> getEntities(){
        return technicianService.getEntities();
    } 

    @PostMapping("")
    public ResponseEntity<TechnicianDTOResponse> storeEntity(@RequestBody TechnicianDTORequest dtoRequest) {
        if (dtoRequest.name().isBlank()) return ResponseEntity.badRequest().build();
        TechnicianDTOResponse entityStored = technicianService.storeEntity(dtoRequest);

        if (entityStored == null) return ResponseEntity.noContent().build();
        return ResponseEntity.status(201).body(entityStored);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnicianDTOResponse> show(@PathVariable("id") Long id) {
        TechnicianDTOResponse technicianId = technicianService.getEntityById(id);
        return ResponseEntity.ok().body(technicianId);
    }

}
