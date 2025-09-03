package dev.paula.api_helpdesk_software.topic;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import dev.paula.api_helpdesk_software.implementation.ITopicService;

@WebMvcTest(TopicController.class)
class TopicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ITopicService<TopicDTOResponse> topicService;

    @Test
    void testGetAllTopics_ShouldReturnListOfTopics() throws Exception {
        List<TopicDTOResponse> topics = Arrays.asList(
            new TopicDTOResponse(1L, "Problema técnico'"),
            new TopicDTOResponse(2L, "Consulta general")
        );

        when(topicService.getAllEntities()).thenReturn(topics);

        mockMvc.perform(get("/api/v1/topics"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1L))
            .andExpect(jsonPath("$[0].name").value("Problema técnico'"))
            .andExpect(jsonPath("$[1].id").value(2L))
            .andExpect(jsonPath("$[1].name").value("Consulta general"));
    }

    @Test
    void testGetTopicById_ShouldReturnTopic() throws Exception {
        TopicDTOResponse topic = new TopicDTOResponse(1L, "Problema técnico'");
        when(topicService.findById(1L)).thenReturn(topic);

        mockMvc.perform(get("/api/v1/topics/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("Problema técnico'"));
    }
}

