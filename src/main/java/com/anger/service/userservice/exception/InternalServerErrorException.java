package com.anger.service.userservice.exception;

/**
 * Custom {@link RuntimeException} that handles 404 Not Found HTTP error codes.
 */
public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException() {
        super("HTTP 500 Internal Server Error");
    }

    public InternalServerErrorException(String message) {
        super(message);
    }

}
