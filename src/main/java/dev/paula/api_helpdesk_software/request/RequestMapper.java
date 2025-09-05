package dev.paula.api_helpdesk_software.request;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import dev.paula.api_helpdesk_software.topic.TopicEntity;

@Component
public class RequestMapper {
    
    public static RequestEntity toEntity(RequestDTORequest dtoRequest, TopicEntity topic) {
        RequestEntity request = new RequestEntity();
        request.setName(dtoRequest.name());
        request.setDateRequest(dtoRequest.dateRequest());
        request.setTopic(topic);
        request.setDescription(dtoRequest.description());
        request.setAttended(dtoRequest.attended());

        return request;
    }

    public static RequestDTOResponse toDTO(RequestEntity entity) {
        RequestDTOResponse dtoResponse = new RequestDTOResponse(
            entity.getId(), 
            entity.getName(), 
            entity.getDateRequest(), 
            entity.getTopic(), 
            entity.getDescription(), 
            entity.getCreatedAt(), 
            entity.isAttended());

        return dtoResponse;
    }
}
