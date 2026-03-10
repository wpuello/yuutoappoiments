package com.yuutoap.Appoiments.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiGeneralResponses<Void>> handleResponseStatus(ResponseStatusException ex) {

        ApiGeneralResponses<Void> response = ApiGeneralResponses.<Void>builder()
                .success(false)
                .message(ex.getReason())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(ex.getStatusCode()).body(response);
    }

    @ExceptionHandler(GlobalResourceNotFoundException.class)
    public ResponseEntity<ApiGeneralResponses<Void>> handleNotFound(GlobalResourceNotFoundException ex) {

        ApiGeneralResponses<Void> response = ApiGeneralResponses.<Void>builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(GlobalConflictException.class)
    public ResponseEntity<ApiGeneralResponses<Void>> handleConflict(GlobalConflictException ex) {

        ApiGeneralResponses<Void> response = ApiGeneralResponses.<Void>builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiGeneralResponses<Void>> handleGeneralException(Exception ex) {

        ApiGeneralResponses<Void> response = ApiGeneralResponses.<Void>builder()
                .success(false)
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiGeneralResponses<Map<String, String>>>
    handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        ApiGeneralResponses<Map<String, String>> response =
                ApiGeneralResponses.<Map<String, String>>builder()
                        .success(false)
                        .message("Errores de validación")
                        .data(errors)
                        .timestamp(LocalDateTime.now())
                        .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
