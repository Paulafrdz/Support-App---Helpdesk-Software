package dev.paula.api_helpdesk_software.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping
    public String index() {
        return "Hello, Spring Boot";
    }
}
