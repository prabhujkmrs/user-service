package com.anger.service.userservice.exception.handler;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * This is used to map a Java exception to an HTTP status with a relevant message.
 */
@Builder
@EqualsAndHashCode
public class HttpExceptionResponse {

    private int status;
    private String message;
    private List<String> errors;

}
