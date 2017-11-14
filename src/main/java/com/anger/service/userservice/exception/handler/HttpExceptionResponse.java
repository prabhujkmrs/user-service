package com.anger.service.userservice.exception.handler;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Builder
@EqualsAndHashCode
@Getter
@Setter
public class HttpExceptionResponse {

    private int status;
    private String message;
    private List<String> errors;

}
