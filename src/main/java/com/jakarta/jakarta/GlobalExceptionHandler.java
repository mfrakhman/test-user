package com.jakarta.jakarta;

import com.jakarta.jakarta.response.Response;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Response<Object>> handleBadCredentials(BadCredentialsException ex) {

        return Response.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Response<Object>> handleResponseStatusException(ResponseStatusException ex) {
        log.error("Response status failed: {}", ex.getMessage(), ex);
        return Response.error(ex.getStatusCode().value(), ex.getReason());
    }
}
