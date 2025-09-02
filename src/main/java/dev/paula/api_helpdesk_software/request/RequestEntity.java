package dev.paula.api_helpdesk_software.request;

import java.time.LocalDate;

import dev.paula.api_helpdesk_software.builder.RequestEntityBuilder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "requests")
public class RequestEntity {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate dateRequest;
    private String themeRequest;
    private String description;


    public RequestEntity() {
    }

    public RequestEntity(Long id, String name, LocalDate dateRequest, String themeRequest, String description ){
        this.id = id;
        this.name = name;
        this.dateRequest = dateRequest;
        this.themeRequest = themeRequest;
        this.description =  description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(LocalDate dateRequest) {
        this.dateRequest = dateRequest;
    }

    public String getThemeRequest() {
        return themeRequest;
    }

    public void setThemeRequest(String themeRequest) {
        this.themeRequest = themeRequest;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static RequestEntityBuilder builder(){
        return new RequestEntityBuilder();
    }

}
