package dev.paula.api_helpdesk_software.request;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.paula.api_helpdesk_software.exceptions.RequestNotFoundExceptions;
import dev.paula.api_helpdesk_software.implementation.IGenericService;
import dev.paula.api_helpdesk_software.topic.TopicEntity;
import dev.paula.api_helpdesk_software.topic.TopicRepository;


@Service
public class RequestServiceImpl implements IGenericService<RequestDTOResponse, RequestDTORequest>{
    
    private final RequestRepository repository;
    private final TopicRepository topicRepository;

    public RequestServiceImpl(RequestRepository repository, TopicRepository topicRepository) {
        this.repository = repository;
        this.topicRepository = topicRepository;
    }

    @Override
    public List<RequestDTOResponse> getEntities() {
        List<RequestDTOResponse> requests = new ArrayList<>();

        repository.findAll().forEach(c -> {
            RequestDTOResponse request = RequestMapper.toDTO(c);
            requests.add(request);
        });
    
        return requests;
    }

    @Override
    public RequestDTOResponse storeEntity(RequestDTORequest requestDTORequest) {
        TopicEntity topic = topicRepository.findById(requestDTORequest.topicId())
            .orElseThrow(() -> new RuntimeException("Tema no encontrado"));
        
        RequestEntity request = RequestMapper.toEntity(requestDTORequest, topic);
        RequestEntity requestStored = repository.save(request);

        return RequestMapper.toDTO(requestStored);
    }

    @Override
    public List<RequestDTOResponse> getEntitiesSortedByDate() {
        return repository.findAllByOrderByCreatedAtAsc()
                         .stream()
                         .map(RequestMapper::toDTO)
                         .toList();
    }

   @Override
    public RequestDTOResponse showById(Long id) {
        return repository.findById(id)
                .map(RequestMapper::toDTO)
                .orElseThrow(() -> new RequestNotFoundExceptions("No se ha encontrado la solicitud con id: " + id ));    
        }
}
