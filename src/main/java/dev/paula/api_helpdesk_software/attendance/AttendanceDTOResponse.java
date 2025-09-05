package dev.paula.api_helpdesk_software.attendance;

import java.time.LocalDateTime;

public record AttendanceDTOResponse(Long id, LocalDateTime attendedAt, Long request, Long technician)  {
    
}
