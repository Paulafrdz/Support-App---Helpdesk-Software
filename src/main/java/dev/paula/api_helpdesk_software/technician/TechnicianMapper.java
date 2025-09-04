package dev.paula.api_helpdesk_software.technician;

public class TechnicianMapper {
    
   public static TechnicianDTOResponse toDTO(TechnicianEntity entity) {

        return new TechnicianDTOResponse(entity.getId(), entity.getNameTechnician());
    }
    
    public static TechnicianEntity toEntity(TechnicianDTORequest dto) {
       
        return new TechnicianEntity(dto.name());
    }
}

