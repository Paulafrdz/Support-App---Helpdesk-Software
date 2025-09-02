package dev.paula.api_helpdesk_software.request;

import org.springframework.stereotype.Component;

@Component
public class RequestMapper {
    
    public static RequestEntity toEntity(RequestDTORequest dtoRequest) {
        RequestEntity request = new RequestEntity();
        request.setName(dtoRequest.name());
        request.setDateRequest(dtoRequest.dateRequest());
        request.setTopic(dtoRequest.topic());
        request.setDescription(dtoRequest.description());

        return request;
    }

    public static RequestDTOResponse toDTO(RequestEntity entity) {
        RequestDTOResponse dtoResponse = new RequestDTOResponse(entity.getId(), entity.getName(), entity.getDateRequest(), entity.getTopic(), entity.getDescription());

        return dtoResponse;
    }
}
