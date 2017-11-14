package com.anger.service.userservice.exception;

/**
 * Custom {@link RuntimeException} that handles 409 Conflict HTTP error codes.
 */
public class ConflictException extends RuntimeException {

    public ConflictException() {
        super("HTTP 409 Conflict");
    }

    public ConflictException(String message) {
        super(message);
    }
}
