package dev.paula.api_helpdesk_software.implementation;

import java.util.List;

public interface IGenericService<T> {
    public List<T> getEntities();
}
