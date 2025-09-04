package dev.paula.api_helpdesk_software.technician;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.paula.api_helpdesk_software.exceptions.TechnicianNotFoundExceptions;
import dev.paula.api_helpdesk_software.exceptions.TopicNotFoundExceptions;
import dev.paula.api_helpdesk_software.technician.TechnicianDTORequest;
import dev.paula.api_helpdesk_software.technician.TechnicianDTOResponse;
import dev.paula.api_helpdesk_software.technician.TechnicianEntity;
import dev.paula.api_helpdesk_software.technician.TechnicianRepository;
import dev.paula.api_helpdesk_software.technician.TechnicianServiceImpl;
import dev.paula.api_helpdesk_software.topic.TopicDTOResponse;
import dev.paula.api_helpdesk_software.topic.TopicEntity;

@ExtendWith(MockitoExtension.class)
public class TechnicianServiceImplTest {

    @InjectMocks
    private TechnicianServiceImpl technicianService;

    @Mock
    private TechnicianRepository repository;

    @BeforeEach
    void setUp() {
        technicianService = new TechnicianServiceImpl(repository);
    }

    @Test
    void testGetTechnicians_ShouldReturnAllEntities() {

        List<TechnicianEntity> requestsMock = List.of(
                new TechnicianEntity("Pepe"),
                new TechnicianEntity("Juan"));

        when(repository.findAll()).thenReturn(requestsMock);
        List<TechnicianDTOResponse> requests = technicianService.getEntities();

        assertThat(requests.size(), is(equalTo(2)));
        assertThat(requests.get(0).name(), is(equalTo("Pepe")));
        assertThat(requests.get(1).name(), is(equalTo("Juan")));

    }

    @Test
    void testStoreEntity_ShouldReturnTechnicianEntity() {

        TechnicianDTORequest dto = new TechnicianDTORequest("Julia");
        TechnicianEntity savedTechnician = new TechnicianEntity("Julia");
        savedTechnician.setId(1L); 

        when(repository.save(Mockito.any(TechnicianEntity.class))).thenReturn(savedTechnician);

        TechnicianDTOResponse storedEntity = technicianService.storeEntity(dto);

        assertThat(storedEntity.name(), is(equalTo("Julia")));
        assertThat(storedEntity.id(), is(equalTo(1L))); 
    }

    @Test
    void testFindById_ShouldThrowException_WhenNotFound() {
        
        when(repository.findById(99L)).thenReturn(Optional.empty());

        TechnicianNotFoundExceptions exception = assertThrows(
            TechnicianNotFoundExceptions.class,
            () -> technicianService.getEntityById(99L)
        );

        assertThat(exception.getMessage(), equalTo("No encontrado el técnico con id: 99"));
    }

}
