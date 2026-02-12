package com.project.AuthenticationService.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception)
    {
        Map<String,String> errors=new HashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error->errors.put(error.getField(),error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleCustomerNotFoundException(UserNotFoundException exception)
    {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public  ResponseEntity<?> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception)
    {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException exception)
    {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception)
    {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
