package dev.paula.api_helpdesk_software.request;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.paula.api_helpdesk_software.exceptions.RequestNotFoundExceptions;
import dev.paula.api_helpdesk_software.topic.TopicEntity;
import dev.paula.api_helpdesk_software.topic.TopicRepository;

@ExtendWith(MockitoExtension.class)
public class RequestServiceImplTest {

    @InjectMocks
    private RequestServiceImpl requestService;

    @Mock
    private RequestRepository repository;

    @Mock
    private TopicRepository topicRepository;

    @BeforeEach
    void setUp() {
        requestService = new RequestServiceImpl(repository, topicRepository);
    }

    @Test
    void testGetRequests_ShouldReturnAllEntities() {

        TopicEntity topic = new TopicEntity("problema");

        List<RequestEntity> requestsMock = List.of(
                new RequestEntity(1L, "Pepe", LocalDate.of(2025, 9, 28), topic, "me da fallo el sistema",
                        LocalDateTime.of(2025, 9, 28, 12, 0)),
                new RequestEntity(1L, "Juan", LocalDate.of(2025, 9, 28), topic, "me da fallo el sistema",
                        LocalDateTime.of(2025, 9, 28, 12, 0)));

        when(repository.findAll()).thenReturn(requestsMock);
        List<RequestDTOResponse> requests = requestService.getEntities();

        assertThat(requests.size(), is(equalTo(2)));
        assertThat(requests.get(0).name(), is(equalTo("Pepe")));
        assertThat(requests.get(1).name(), is(equalTo("Juan")));

    }

    @Test
    void testStoreEntity_ShouldReturnRequestEntity() {

        TopicEntity topic = new TopicEntity("Problema técnico");
        topic.setId(2L); 

        RequestDTORequest dto = new RequestDTORequest("Julia", LocalDate.of(2025, 8, 29), 2L, "El sistema da problemas");

        RequestEntity savedEntity = new RequestEntity(3L, "Julia", LocalDate.of(2025, 8, 29), topic, "El sistema da problemas", LocalDateTime.of(2025, 8, 29, 10, 0));

        when(topicRepository.findById(2L)).thenReturn(Optional.of(topic));
        when(repository.save(Mockito.any(RequestEntity.class))).thenReturn(savedEntity);
    
        RequestDTOResponse storedEntity = requestService.storeEntity(dto);

        assertThat(storedEntity.id(), is(equalTo(3L)));
        assertThat(storedEntity.name(), is(equalTo("Julia")));
        assertThat(storedEntity.dateRequest(), is(equalTo(LocalDate.of(2025, 8, 29))));
        assertThat(storedEntity.topic().getId(), is(equalTo(2L)));
        assertThat(storedEntity.description(), is(equalTo("El sistema da problemas")));
    }

    @Test
    void testGetRequestById_ReturnException_WhenRequestNotFound() {
       Long id = 0L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        RequestNotFoundExceptions exception = assertThrows(
        RequestNotFoundExceptions.class,
        () -> requestService.showById(id));

        assertThat(exception.getMessage(), is(equalTo("No se ha encontrado la solicitud con id: " + id )));
    }

}
