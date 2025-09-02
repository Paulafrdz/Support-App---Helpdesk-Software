package dev.paula.api_helpdesk_software.builder;

import java.time.LocalDate;

import dev.paula.api_helpdesk_software.request.RequestEntity;
import dev.paula.api_helpdesk_software.topic.TopicEntity;

public interface IRequestBuilder {

    public RequestEntityBuilder id(Long id);
    public RequestEntityBuilder name(String name);
    public RequestEntityBuilder dateRequest(LocalDate dateRequest);
    public RequestEntityBuilder topic(TopicEntity topic);
    public RequestEntityBuilder description(String description);
    
    public RequestEntity build();
}
