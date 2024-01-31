package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Global exception handler for handling common exceptions across the application.
 */
@ControllerAdvice
public class CentralExceptionHandler {

    /**
     * Handles validation errors for method arguments.
     *
     * @param methodArgumentNotValidException The exception containing validation error details.
     * @return ResponseEntity with the validation error message and a status code of BAD_REQUEST.
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
        return new ResponseEntity<>(methodArgumentNotValidException.getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }
}
