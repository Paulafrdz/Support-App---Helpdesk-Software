package dev.paula.api_helpdesk_software.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import dev.paula.api_helpdesk_software.topic.TopicEntity;

public record RequestDTOResponse(
    Long id, 
    String name, 
    LocalDate dateRequest, 
    TopicEntity topic, 
    String description,
    LocalDateTime created_at, 
    boolean attended) {
} 