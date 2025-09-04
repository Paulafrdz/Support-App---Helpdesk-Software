package dev.paula.api_helpdesk_software.technician;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

import dev.paula.api_helpdesk_software.technician.TechnicianEntity;


public class TechnicianEntityTest {

    @Test
    void testNoArgsConstructorAndSetters() {
        
        TechnicianEntity technician = new TechnicianEntity();
        technician.setNameTechnician("Pepe");

        assertThat(technician.getId()).isNull(); 
        assertThat(technician.getNameTechnician()).isEqualTo("Pepe");
    }

    @Test
    void testTechnicianEntity() {
        TechnicianEntity technician = new TechnicianEntity();

        technician.setId(1L);
        technician.setNameTechnician("Juan Rodriguez");

        assertThat(technician.getId(), is(equalTo(1L)));
        assertThat(technician.getNameTechnician(), is(equalTo("Juan Rodriguez")));
    } 
}
  

