package com.jakarta.jakarta.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@AllArgsConstructor
public class Response<T> {
    private final int status;
    private final String message;
    private final T data;
    private final Instant timestamp;

    public static <T> ResponseEntity<Response<T>> success(int status, String message, T data) {
        Response<T> response = new Response<>(status, message, data, Instant.now().truncatedTo(ChronoUnit.SECONDS));
        return ResponseEntity.status(status).body(response);
    }

    public static <T> ResponseEntity<Response<T>> success(String message, T data) {
        return success(HttpStatus.OK.value(), message, data);
    }

    public static <T> ResponseEntity<Response<T>> success(int status, String message) {
        return success(HttpStatus.OK.value(), message, null);
    }

    public static <T> ResponseEntity<Response<T>> success(String message) {
        return success(message, null);
    }

    public static <T> ResponseEntity<Response<T>> error(int status, String message, T data) {
        Response<T> response = new Response<>(status, message, data, Instant.now().truncatedTo(ChronoUnit.SECONDS));
        return ResponseEntity.status(status).body(response);
    }

    public static <T> ResponseEntity<Response<T>> error(int status, String message) {
        return error(status, message, null);
    }

    public static <T> ResponseEntity<Response<T>> error(String message) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }

    public static <T> ResponseEntity<Response<T>> error(T data) {
        return error(HttpStatus.BAD_REQUEST.value(), "Bad request", data);
    }
}
