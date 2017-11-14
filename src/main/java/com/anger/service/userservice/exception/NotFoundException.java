package com.anger.service.userservice.exception;

/**
 * Custom {@link RuntimeException} that handles 404 Not Found HTTP error codes.
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("HTTP 404 Not Found");
    }

    public NotFoundException(String message) {
        super(message);
    }

}
