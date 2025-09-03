package dev.paula.api_helpdesk_software.topic;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<TopicEntity, Long>{
    
}
