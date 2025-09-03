package dev.paula.api_helpdesk_software.exceptions;

public class TopicExceptions extends RuntimeException{
    
    public TopicExceptions(String message) {
        super(message);
    }

    public TopicExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
