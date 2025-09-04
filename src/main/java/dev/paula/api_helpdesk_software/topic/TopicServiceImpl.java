package dev.paula.api_helpdesk_software.topic;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import dev.paula.api_helpdesk_software.exceptions.TopicNotFoundExceptions;
import dev.paula.api_helpdesk_software.implementation.ITopicService;

@Service
public class TopicServiceImpl  implements ITopicService<TopicDTOResponse> {
    
    private final TopicRepository repository;

    public TopicServiceImpl(TopicRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TopicDTOResponse> getAllEntities() {
        return repository.findAll()
                        .stream()
                        .map(TopicMapper::toDTO)
                        .collect(Collectors.toList());
    }

    @Override
    public TopicDTOResponse getEntityById(Long id) {
        TopicEntity topic = repository.findById(id).orElseThrow(() -> new TopicNotFoundExceptions("Tema no encontrado con id " + id + " no existe."));
        return TopicMapper.toDTO(topic);
    }
}
