package dev.paula.api_helpdesk_software.exceptions;

public class RequestExceptions extends RuntimeException {
    public RequestExceptions(String message) {
        super(message);
    }

    public RequestExceptions(String message, Throwable cause) {
        super(message, cause);
    }

}

