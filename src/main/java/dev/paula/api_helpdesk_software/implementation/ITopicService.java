package dev.paula.api_helpdesk_software.implementation;

import java.util.List;

public interface ITopicService <T>{

    public List<T> getAllEntities();
    public T getEntityById(Long id);
    
} 