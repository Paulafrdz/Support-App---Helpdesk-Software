package dev.paula.api_helpdesk_software.request;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = ("${api-endpoint}/requests"))
public class RequestController {
    
    @GetMapping("")
    public RequestEntity index(){
        RequestEntity request1 = new RequestEntity(1L,"Pepe", LocalDate.of(2025, 9, 28), "problema", "me da fallo el sistema");

        return request1;
    } 
}
