package com.petproject.portfolio.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error> handleNotFound(RuntimeException ex) {
        return ResponseEntity.badRequest().body(new Error(ex.getMessage(), BAD_REQUEST));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Error> handleConflict(RuntimeException ex) {
        return ResponseEntity.badRequest().body(new Error(ex.getMessage(), CONFLICT));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Optional<FieldError> fieldError = Optional.ofNullable(ex.getBindingResult().getFieldError());
        return ResponseEntity.badRequest().body(new Error(fieldError.map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse(null), BAD_REQUEST));
    }
}

