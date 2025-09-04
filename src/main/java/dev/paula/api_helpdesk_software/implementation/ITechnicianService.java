package dev.paula.api_helpdesk_software.implementation;

import java.util.List;

public interface ITechnicianService<T,S> {

    public List<T> getAllEntities();
    public T create(S dto);

} 