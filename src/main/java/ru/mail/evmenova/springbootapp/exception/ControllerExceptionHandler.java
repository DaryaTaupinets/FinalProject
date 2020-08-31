package ru.mail.evmenova.springbootapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Optional;

@ControllerAdvice(basePackages = "ru.mail.evmenova.springbootapp.controller")
public class ControllerExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> handleRestException(ApiException exception) {
        return new ResponseEntity<>(Optional.ofNullable(exception.getMessage()).orElse("Something went wrong."),
                Optional.ofNullable(exception.getStatus()).orElse(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAnyException(Exception exception) {
        return new ResponseEntity<>(Optional.ofNullable(exception.getMessage()).orElse("Something went wrong."),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleNotValidException(MethodArgumentNotValidException exception) {
        String message = "Following constraint violations were found: " +
                String.join(";\n", exception.getBindingResult().getAllErrors()
                        .stream()
                        .map(ObjectError::getDefaultMessage)
                        .toArray(String[]::new));
        return new ResponseEntity<>(message,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException exception) {
        String message = "Following constraint violations were found: " +
                String.join(";\n", exception.getConstraintViolations()
                        .stream()
                        .map(ConstraintViolation::getMessage)
                        .toArray(String[]::new));
        return new ResponseEntity<>(message,
                HttpStatus.BAD_REQUEST);
    }
}
