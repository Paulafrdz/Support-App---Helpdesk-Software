package dev.paula.api_helpdesk_software.builder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import dev.paula.api_helpdesk_software.request.RequestEntity;

public class RequestEntityBuilderTest {
    @Test
    void testRequestEntityBuilderWithStategy1(){

        RequestEntity request = RequestEntity.builder()
            .id(1L)
            .name("Pepe")
            .description("me da fallo el sistema")
            .dateRequest(LocalDate.of(2025, 9, 28))
            .themeRequest("problema")
            .build();

        
        assertThat(request, is(instanceOf(RequestEntity.class)));
        assertThat(request.getId(), is(1L));
        assertThat(request.getName(), is("Pepe"));
        assertThat(request.getDescription(), is("me da fallo el sistema"));
        assertThat(request.getDateRequest(), is(LocalDate.of(2025, 9, 28)));
        assertThat(request.getThemeRequest(), is("problema"));
        
    }
}
