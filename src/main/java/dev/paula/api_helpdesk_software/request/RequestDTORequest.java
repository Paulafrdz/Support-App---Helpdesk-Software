package dev.paula.api_helpdesk_software.request;

import java.time.LocalDate;

import dev.paula.api_helpdesk_software.topic.TopicEntity;

public record RequestDTORequest(String name, LocalDate dateRequest, TopicEntity topic, String description) {
}
