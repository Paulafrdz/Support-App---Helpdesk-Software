package dev.paula.api_helpdesk_software.topic;

public class TopicMapper {
    
    public static TopicDTOResponse toDTO(TopicEntity topic) {
        return new TopicDTOResponse(topic.getId(), topic.getName());
    }
}
