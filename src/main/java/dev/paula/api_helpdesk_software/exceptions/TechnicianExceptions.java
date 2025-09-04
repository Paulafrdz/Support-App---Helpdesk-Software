package dev.paula.api_helpdesk_software.exceptions;

public class TechnicianExceptions extends RuntimeException {
    public TechnicianExceptions(String message) {
        super(message);
    }

    public TechnicianExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
