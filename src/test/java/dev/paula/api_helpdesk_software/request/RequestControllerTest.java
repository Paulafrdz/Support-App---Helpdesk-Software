package dev.paula.api_helpdesk_software.request;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.paula.api_helpdesk_software.implementation.IGenericService;
import dev.paula.api_helpdesk_software.topic.TopicEntity;

@WebMvcTest(controllers = RequestController.class)
public class RequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IGenericService<RequestDTOResponse, RequestDTORequest> requestService;

    @Autowired
    ObjectMapper mapper;

    @Test
    @DisplayName("Should return all requests")
    void textIndex_ShouldReturnARequests() throws Exception{

        TopicEntity topic = new TopicEntity( "problema");


        RequestDTOResponse request1 = new RequestDTOResponse(1L,"Pepe", LocalDate.of(2025, 9, 28), topic, "me da fallo el sistema");
        RequestDTOResponse request2 = new RequestDTOResponse(1L,"Juan", LocalDate.of(2025, 2, 18), topic, "fallo el sistema");
        List<RequestDTOResponse> requests = List.of(request1, request2);
        String json = mapper.writeValueAsString(requests);

        when(requestService.getEntities()).thenReturn(requests);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/requests"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).isEqualTo(json);

    }

    @Test
    void testStore_ShouldReturnStatus201() throws Exception{
        
        TopicEntity topic = new TopicEntity("problema");

        RequestDTORequest dto = new RequestDTORequest("Pepe", LocalDate.of(2025, 9, 28), topic, "me da fallo el sistema");
        RequestDTOResponse request1 = new RequestDTOResponse(1L,"Pepe", LocalDate.of(2025, 9, 28), topic, "me da fallo el sistema");
        String json = mapper.writeValueAsString(dto);

        when(requestService.storeEntity(dto)).thenReturn(request1);
        MockHttpServletResponse response = mockMvc.perform(post("/api/v1/requests").content(json).contentType("application/json"))
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse();

        assertThat(response.getContentAsString(), containsString(request1.name()));

    }

    @Test
    void testStoreRequest_ShouldReturnStatus400_IfNameIsEmpty() throws Exception {
        
        TopicEntity topic = new TopicEntity("problema");

        RequestDTORequest dto = new RequestDTORequest("", LocalDate.of(2025, 9, 28), topic, "me da fallo el sistema");
        String json = mapper.writeValueAsString(dto);
        when(requestService.storeEntity(dto)).thenReturn(null);
        mockMvc.perform(post("/api/v1/requests").content(json).contentType("application/json"))
            .andExpect(status().isBadRequest());
    }

    @Test
    void testStoreRequest_ShouldReturnNoContent_IfServiceDoesNotReturnAnyValue() throws Exception {
        
        TopicEntity topic = new TopicEntity("problema");

        RequestDTORequest dto = new RequestDTORequest("Pepe", LocalDate.of(2025, 9, 28), topic, "me da fallo el sistema");
        String json = mapper.writeValueAsString(dto);

        when(requestService.storeEntity(dto)).thenReturn(null);
        mockMvc.perform(post("/api/v1/requests").content(json).contentType("application/json"))
                .andExpect(status().isNoContent());
    }
}   
