package dev.paula.api_helpdesk_software.technician;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicianRepository extends JpaRepository<TechnicianEntity, Long> {
    
}
