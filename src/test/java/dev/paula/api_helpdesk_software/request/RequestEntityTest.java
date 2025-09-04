package dev.paula.api_helpdesk_software.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import dev.paula.api_helpdesk_software.topic.TopicEntity;

public class RequestEntityTest {
  
    @Test
    void testRequestEntity_InitializationWithIdAndNameAndDateRequestAndThemeRequestAndDescription(){

        TopicEntity topic = new TopicEntity( "problema");
        RequestEntity request = new RequestEntity(1L,"Pepe", LocalDate.of(2025, 9, 28), topic, "me da fallo el sistema", LocalDateTime.of(2025, 9, 28, 12, 0, 0), true);

        assertThat(request).isInstanceOf(RequestEntity.class);
        assertThat(request.getClass().getDeclaredFields().length, is(equalTo(7)));

    }

    @Test
    void testRequestEntity() {
        RequestEntity request = new RequestEntity();
        request.setId(1L);
        request.setName("Pepe");
        request.setDateRequest(LocalDate.of(2025, 8, 28));
        
        TopicEntity topic = new TopicEntity("problema");
        request.setTopic(topic);

        request.setDescription("me da fallo el sistema");

        assertThat(request.getId(), is(equalTo(1L)));
        assertThat(request.getName(), is(equalTo("Pepe")));
        assertThat(request.getDateRequest(), is(equalTo(LocalDate.of(2025, 8, 28))));
        assertThat(request.getTopic().getName(), is("problema"));
        assertThat(request.getDescription(), is(equalTo("me da fallo el sistema")));

    }
}
