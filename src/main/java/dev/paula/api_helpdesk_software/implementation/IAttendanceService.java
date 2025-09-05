package dev.paula.api_helpdesk_software.implementation;

import dev.paula.api_helpdesk_software.attendance.AttendanceDTORequest;
import dev.paula.api_helpdesk_software.attendance.AttendanceDTOResponse;

public interface IAttendanceService {
    AttendanceDTOResponse markAsAttended(AttendanceDTORequest dtoRequest);
}
