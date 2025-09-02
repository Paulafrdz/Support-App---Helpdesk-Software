package dev.paula.api_helpdesk_software.builder;

import java.time.LocalDate;

import dev.paula.api_helpdesk_software.request.RequestEntity;

public interface IRequestBuilder {

    public RequestEntityBuilder id(Long id);
    public RequestEntityBuilder name(String name);
    public RequestEntityBuilder dateRequest(LocalDate dateRequest);
    public RequestEntityBuilder themeRequest(String themeRequest);
    public RequestEntityBuilder description(String description);
    
    public RequestEntity build();
}
