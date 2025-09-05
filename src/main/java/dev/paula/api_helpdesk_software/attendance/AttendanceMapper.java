package dev.paula.api_helpdesk_software.attendance;

import dev.paula.api_helpdesk_software.request.RequestEntity;
import dev.paula.api_helpdesk_software.technician.TechnicianEntity;

public class AttendanceMapper {
    
    public static AttendanceEntity toEntity(AttendanceDTORequest dtoRequest, RequestEntity request, TechnicianEntity technician) {
        AttendanceEntity attendance = new AttendanceEntity();
        attendance.setAttendedAt(dtoRequest.attendedAt());
        attendance.setRequest(request);
        attendance.setTechnician(technician);

        return attendance;
    }

    public static AttendanceDTOResponse toDTO(AttendanceEntity entity) {
        return new AttendanceDTOResponse(
        entity.getId(),
        entity.getAttendedAt(),
        entity.getRequest().getId(),
        entity.getTechnician().getId()
        );
    }
}
