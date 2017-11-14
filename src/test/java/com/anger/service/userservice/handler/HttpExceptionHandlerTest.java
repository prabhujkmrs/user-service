package com.anger.service.userservice.handler;


import com.anger.service.userservice.exception.ConflictException;
import com.anger.service.userservice.exception.InternalServerErrorException;
import com.anger.service.userservice.exception.NotFoundException;
import com.anger.service.userservice.exception.handler.HttpExceptionHandler;
import com.anger.service.userservice.exception.handler.HttpExceptionResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HttpExceptionHandlerTest {

    @Mock
    private MethodArgumentNotValidException exception;

    @Mock
    private BeanPropertyBindingResult bindingResult;

    private HttpExceptionHandler httpExceptionHandler = new HttpExceptionHandler();

    @Test
    public void shouldTestNotFoundException() {
        ResponseEntity<HttpExceptionResponse> entity
                = httpExceptionHandler.handleNotFoundException(new NotFoundException());

        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());

        HttpExceptionResponse exceptionResponse = entity.getBody();
        assertEquals(HttpStatus.NOT_FOUND.value(), exceptionResponse.getStatus());
        assertEquals("HTTP 404 Not Found", exceptionResponse.getMessage());
    }

    @Test
    public void shouldTestInternalServerErrorException() {
        ResponseEntity<HttpExceptionResponse> entity
                = httpExceptionHandler.handleInternalServerErrorException(new InternalServerErrorException());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, entity.getStatusCode());

        HttpExceptionResponse exceptionResponse = entity.getBody();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), exceptionResponse.getStatus());
        assertEquals("HTTP 500 Internal Server Error", exceptionResponse.getMessage());
    }

    @Test
    public void shouldTestConflictException() {
        ResponseEntity<HttpExceptionResponse> entity
                = httpExceptionHandler.handleConflictErrorException(new ConflictException());

        assertEquals(HttpStatus.CONFLICT, entity.getStatusCode());

        HttpExceptionResponse exceptionResponse = entity.getBody();
        assertEquals(HttpStatus.CONFLICT.value(), exceptionResponse.getStatus());
        assertEquals("HTTP 409 Conflict", exceptionResponse.getMessage());
    }

    @Test
    public void shouldTestServletRequestBindingException() {
        ResponseEntity<HttpExceptionResponse> entity
                = httpExceptionHandler.handleBadRequestValidationErrors(new ServletRequestBindingException("error"));

        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());

        HttpExceptionResponse exceptionResponse = entity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST.value(), exceptionResponse.getStatus());
        assertEquals("Bad Request", exceptionResponse.getMessage());
        assertEquals(1, exceptionResponse.getErrors().size());
        assertEquals("error", exceptionResponse.getErrors().get(0));
    }

    @Test
    public void shouldTestMethodArgumentNotValidException() {

        String field = "name";
        String errorMessage = "is missing";

        when(exception.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors())
                .thenReturn(Arrays.asList(new FieldError("object", field, errorMessage)));

        ResponseEntity<HttpExceptionResponse> entity = httpExceptionHandler.handleBadRequestValidationErrors(exception);

        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());

        HttpExceptionResponse exceptionResponse = entity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST.value(), exceptionResponse.getStatus());
        assertEquals("Bad Request", exceptionResponse.getMessage());
        assertEquals(1, exceptionResponse.getErrors().size());
        assertEquals(String.format("Parameter %s %s", field, errorMessage), exceptionResponse.getErrors().get(0));
    }
}