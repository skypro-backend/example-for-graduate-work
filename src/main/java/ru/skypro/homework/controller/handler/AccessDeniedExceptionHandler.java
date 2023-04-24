package ru.skypro.homework.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.skypro.homework.exception.WrongPasswordException;

@ControllerAdvice
public class AccessDeniedExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> accessDeniedExceptionHandler(){
        return ResponseEntity.status(403).build();
        }
    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<?> wrongPasswordExceptionHandler(){
        return ResponseEntity.status(403).build();
    }
}
