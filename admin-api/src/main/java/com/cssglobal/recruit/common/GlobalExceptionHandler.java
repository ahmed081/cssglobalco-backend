package com.cssglobal.recruit.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    ResponseEntity<Map<String, Object>> api(ApiException ex) {
        return body(ex.getStatus(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Map<String, Object>> validation(MethodArgumentNotValidException ex) {
        return body(HttpStatus.BAD_REQUEST, "Invalid request");
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<Map<String, Object>> generic(Exception ex) {
        return body(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    private ResponseEntity<Map<String, Object>> body(HttpStatus status, String msg) {
        return ResponseEntity.status(status).body(Map.of("timestamp", Instant.now(), "status", status.value(), "error", status.getReasonPhrase(), "message", msg));
    }
}
