package dev.paula.api_helpdesk_software.topic;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.paula.api_helpdesk_software.exceptions.TopicNotFoundExceptions;

@ExtendWith(MockitoExtension.class)
class TopicServiceImplTest {

    @Mock
    private TopicRepository repository;

    @InjectMocks
    private TopicServiceImpl topicService;

    @Test
    void testGetAllEntities_ShouldReturnListOfTopics() {
        // Arrange
        TopicEntity topic1 = new TopicEntity("Problema técnico");
        topic1.setId(1L);
        TopicEntity topic2 = new TopicEntity("Consulta general");
        topic2.setId(2L);

        when(repository.findAll()).thenReturn(Arrays.asList(topic1, topic2));

        // Act
        List<TopicDTOResponse> result = topicService.getAllEntities();

        // Assert
        assertThat(result.size(), equalTo(2));
        assertThat(result.get(0).id(), equalTo(1L));
        assertThat(result.get(0).name(), equalTo("Problema técnico"));
        assertThat(result.get(1).id(), equalTo(2L));
        assertThat(result.get(1).name(), equalTo("Consulta general"));
    }

    @Test
    void testFindById_ShouldReturnTopic_WhenExists() {
        // Arrange
        TopicEntity topic = new TopicEntity("Problema técnico");
        topic.setId(1L);
        when(repository.findById(1L)).thenReturn(Optional.of(topic));

        // Act
        TopicDTOResponse result = topicService.findById(1L);

        // Assert
        assertThat(result.id(), equalTo(1L));
        assertThat(result.name(), equalTo("Problema técnico"));
    }

    @Test
    void testFindById_ShouldThrowException_WhenNotFound() {
        // Arrange
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        TopicNotFoundExceptions exception = assertThrows(
            TopicNotFoundExceptions.class,
            () -> topicService.findById(99L)
        );

        assertThat(exception.getMessage(), equalTo("Tema no encontrado con id 99 no existe."));
    }
}
