package dev.paula.api_helpdesk_software.request;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.paula.api_helpdesk_software.implementation.IGenericService;

@RestController
@RequestMapping(path = ("${api-endpoint}/requests"))
public class RequestController {

    private final IGenericService<RequestEntity> service;

    public RequestController(IGenericService<RequestEntity> service) {
        this.service = service;
    }
    
    
    @GetMapping("")
    public List<RequestEntity> index(){
        // RequestEntity request1 = new RequestEntity(1L,"Pepe", LocalDate.of(2025, 9, 28), "problema", "me da fallo el sistema");

        return service.getEntities();
    } 
}
