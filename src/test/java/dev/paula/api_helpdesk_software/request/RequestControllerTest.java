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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.paula.api_helpdesk_software.implementation.IGenericService;

@WebMvcTest(controllers = RequestController.class)
public class RequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IGenericService<RequestEntity> requestService;

    @Autowired
    ObjectMapper mapper;

    @Test
    @DisplayName("Should return all requests")
    void textIndex_ShouldReturnARequests() throws Exception{
        RequestEntity request1 = new RequestEntity(1L,"Pepe", LocalDate.of(2025, 9, 28), "problema", "me da fallo el sistema");
        RequestEntity request2 = new RequestEntity(1L,"Juan", LocalDate.of(2025, 2, 18), "erro", "fallo el sistema");
        List<RequestEntity> requests = List.of(request1, request2);
        String json = mapper.writeValueAsString(requests);

        when(requestService.getEntities()).thenReturn(requests);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/requests"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).isEqualTo(json);

    }
}   
