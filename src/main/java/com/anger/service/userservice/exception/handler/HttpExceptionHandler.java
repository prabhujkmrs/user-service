package com.anger.service.userservice.exception.handler;

import com.anger.service.userservice.exception.ConflictException;
import com.anger.service.userservice.exception.InternalServerErrorException;
import com.anger.service.userservice.exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;


@ControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<HttpExceptionResponse> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(NOT_FOUND).body(
            HttpExceptionResponse.builder()
                .status(NOT_FOUND.value())
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseBody
    public ResponseEntity<HttpExceptionResponse> handleInternalServerErrorException(InternalServerErrorException ex) {
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
            HttpExceptionResponse.builder()
                .status(INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseBody
    public ResponseEntity<HttpExceptionResponse> handleConflictErrorException(ConflictException ex) {
        return ResponseEntity.status(CONFLICT).body(
            HttpExceptionResponse.builder()
                .status(CONFLICT.value())
                .message(ex.getMessage())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<HttpExceptionResponse> handleBadRequestValidationErrors(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(
            HttpExceptionResponse.builder()
                .status(BAD_REQUEST.value())
                .message(BAD_REQUEST.getReasonPhrase())
                .errors(getValidationErrors(ex.getBindingResult()))
                .build());
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseBody
    public ResponseEntity<HttpExceptionResponse> handleBadRequestValidationErrors(ServletRequestBindingException ex) {
        return ResponseEntity.status(BAD_REQUEST).body(
            HttpExceptionResponse.builder()
                .status(BAD_REQUEST.value())
                .message(BAD_REQUEST.getReasonPhrase())
                .errors(Collections.singletonList(ex.getMessage()))
                .build());
    }

    private List<String> getValidationErrors(BindingResult result) {
        return result.getAllErrors().stream()
            .map(e -> String.format("Parameter %s %s", ((FieldError) e).getField(), e.getDefaultMessage()))
            .collect(Collectors.toList());
    }
}
