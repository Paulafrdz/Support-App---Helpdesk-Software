package dev.paula.api_helpdesk_software.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No se ha encontrado el tema")
public class TopicNotFoundExceptions extends TopicExceptions{
    
    public TopicNotFoundExceptions(String message) {
        super(message);
    }

    public TopicNotFoundExceptions(String message, Throwable cause) {
        super(message, cause);
    }
    
}
