package dev.paula.api_helpdesk_software.technician;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.paula.api_helpdesk_software.implementation.IGenericService;
import dev.paula.api_helpdesk_software.implementation.ITechnicianService;
import dev.paula.api_helpdesk_software.technician.TechnicianDTORequest;
import dev.paula.api_helpdesk_software.technician.TechnicianDTOResponse;
import dev.paula.api_helpdesk_software.technician.TechnicianEntity;
import dev.paula.api_helpdesk_software.topic.TopicEntity;


@WebMvcTest(controllers = TechnicianController.class)
public class TechnicianControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ITechnicianService<TechnicianDTOResponse, TechnicianDTORequest> technicianService;

    @Autowired
    ObjectMapper mapper;

     @Test
    @DisplayName("Should return all technician")
    void textGetEntities_ShouldReturnAllTechnicians() throws Exception{

        TechnicianDTOResponse request1 = new TechnicianDTOResponse(1L,"Pepe");
        TechnicianDTOResponse request2 = new TechnicianDTOResponse(1L,"Juan");
        List<TechnicianDTOResponse> requests = List.of(request1, request2);
        String json = mapper.writeValueAsString(requests);

        when(technicianService.getEntities()).thenReturn(requests);
        MockHttpServletResponse response = mockMvc.perform(get("/api/v1/technician"))
        .andExpect(status().isOk())
        .andReturn()
        .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentAsString()).isEqualTo(json);

    }

    @Test
    void testStore_ShouldReturnStatus201() throws Exception{
        
        TechnicianDTORequest dto = new TechnicianDTORequest("Pepe");
        TechnicianDTOResponse request1 = new TechnicianDTOResponse(1L,"Pepe");
        String json = mapper.writeValueAsString(dto);

        when(technicianService.storeEntity(any(TechnicianDTORequest.class))).thenReturn(request1);
        MockHttpServletResponse response = mockMvc.perform(post("/api/v1/technician").content(json).contentType("application/json"))
            .andExpect(status().isCreated())
            .andReturn()
            .getResponse();

        assertThat(response.getContentAsString(), containsString(request1.name()));

    }
}
