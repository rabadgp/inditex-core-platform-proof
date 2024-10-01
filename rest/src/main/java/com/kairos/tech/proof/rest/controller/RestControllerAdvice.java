package com.kairos.tech.proof.rest.controller;

import com.kairos.tech.proof.application.exception.FindProductException;
import org.openapitools.model.ApiErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.time.ZoneOffset;

@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler({FindProductException.class})
    public ResponseEntity<ApiErrorResponse> handleFindProductException(FindProductException ex, WebRequest request) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                Instant.now().atZone(ZoneOffset.UTC).toInstant(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ((ServletWebRequest) request).getRequest().getRequestURI(),
                ex.getMessage()
        );
        return new ResponseEntity<>(
                apiErrorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiErrorResponse> handleTypeMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String name = ex.getName();
        String type = ex.getRequiredType().getSimpleName();
        Object value = ex.getValue();
        String message = String.format("'%s' property does not have a valid value ('%s') of type '%s'",
                name, value, type);
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                Instant.now().atZone(ZoneOffset.UTC).toInstant(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ((ServletWebRequest) request).getRequest().getRequestURI(),
                message
        );
        return new ResponseEntity<>(
                apiErrorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiErrorResponse> handleMissingRequestParameterException(MissingServletRequestParameterException ex, WebRequest request) {
        String message = String.format("'%s' parameter is missing", ex.getParameterName());
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                Instant.now().atZone(ZoneOffset.UTC).toInstant(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ((ServletWebRequest) request).getRequest().getRequestURI(),
                message
        );
        return new ResponseEntity<>(
                apiErrorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
