package dev.paula.api_helpdesk_software.request;

import java.time.LocalDate;

public record RequestDTOResponse(Long id, String name, LocalDate dateRequest, String themeRequest, String description) {
} 