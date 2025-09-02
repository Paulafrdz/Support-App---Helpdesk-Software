package dev.paula.api_helpdesk_software.builder;

import dev.paula.api_helpdesk_software.request.RequestEntity;
import java.time.LocalDate;

public class RequestEntityBuilder implements IRequestBuilder {
    
    private final RequestEntity request;

    public RequestEntityBuilder() {
        this.request = new RequestEntity();
    }

    @Override
    public RequestEntityBuilder id(Long id) {
        request.setId(id);
        return this;
    }

    @Override
    public RequestEntityBuilder name(String name){
        request.setName(name);
        return this;
    }

    @Override
    public RequestEntityBuilder description(String description) {
        request.setDescription(description);
        return this;
    }

    @Override
    public RequestEntityBuilder dateRequest(LocalDate dateRequest) {
        request.setDateRequest(dateRequest);
        return this;
    }

    @Override
    public RequestEntityBuilder themeRequest(String themeRequest) {
        request.setThemeRequest(themeRequest);
        return this;
    }

    @Override
    public RequestEntity build() {
        return request;
    }
}
