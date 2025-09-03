package dev.paula.api_helpdesk_software.topic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TopicEntityTest {

    @Test
    void testNoArgsConstructorAndSetters() {
        TopicEntity topic = new TopicEntity();
        topic.setName("Problema");

        assertThat(topic.getId()).isNull(); 
        assertThat(topic.getName()).isEqualTo("Problema");
    }

    @Test
    void testConstructorWithName() {
        TopicEntity topic = new TopicEntity("Consulta");

        assertThat(topic.getId()).isNull(); 
        assertThat(topic.getName()).isEqualTo("Consulta");
    }
}
