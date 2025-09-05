package dev.paula.api_helpdesk_software.attendance;

import org.springframework.stereotype.Service;

import dev.paula.api_helpdesk_software.exceptions.RequestExceptions;
import dev.paula.api_helpdesk_software.exceptions.TechnicianNotFoundExceptions;
import dev.paula.api_helpdesk_software.implementation.IAttendanceService;
import dev.paula.api_helpdesk_software.request.RequestEntity;
import dev.paula.api_helpdesk_software.request.RequestRepository;
import dev.paula.api_helpdesk_software.technician.TechnicianEntity;
import dev.paula.api_helpdesk_software.technician.TechnicianRepository;


@Service
public class AttendanceServiceImpl implements IAttendanceService{
    
    private final AttendanceRepository attendanceRepository;
    private final RequestRepository requestRepository;
    private final TechnicianRepository technicianRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository, RequestRepository requestRepository, TechnicianRepository technicianRepository) {
        this.attendanceRepository = attendanceRepository;
        this.requestRepository = requestRepository;
        this.technicianRepository = technicianRepository;
    }

    @Override
    public AttendanceDTOResponse markAsAttended(AttendanceDTORequest dtoRequest) {
        RequestEntity request = requestRepository.findById(dtoRequest.request())
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        if (request.isAttended()) {
            throw new RequestExceptions("La solicitud ya está atendida");
        }

        TechnicianEntity technician = technicianRepository.findById(dtoRequest.technician())
                .orElseThrow(() -> new TechnicianNotFoundExceptions("Técnico no encontrado"));

        AttendanceEntity attendance = AttendanceMapper.toEntity(dtoRequest, request, technician);

        AttendanceEntity savedAttendance = attendanceRepository.save(attendance);

        request.setAttended(true);
        requestRepository.save(request);

        return AttendanceMapper.toDTO(savedAttendance);
    }
}
