package dev.paula.api_helpdesk_software.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

    
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Solicitud no encontrada")
public class RequestNotFoundExceptions extends RequestExceptions{

    public RequestNotFoundExceptions(String message) {
        super(message);
    }

    public RequestNotFoundExceptions(String message, Throwable cause) {
        super(message, cause);
    }
    
}

