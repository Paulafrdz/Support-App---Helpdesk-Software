package dev.paula.api_helpdesk_software.implementation;

import java.util.List;

public interface ITechnicianService<T, S> {
    public List<T> getEntities();
    public T storeEntity(S dto);
    public T getEntityById(Long id);

} 
