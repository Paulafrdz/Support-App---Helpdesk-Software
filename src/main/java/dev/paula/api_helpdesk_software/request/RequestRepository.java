package dev.paula.api_helpdesk_software.request;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<RequestEntity, Long>{

    List<RequestEntity> findAllByOrderByCreatedAtAsc();
} 
