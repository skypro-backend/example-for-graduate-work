package ru.skypro.homework.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.skypro.homework.exception.NotAuthorizedException;


@ControllerAdvice
public class NotAuthorizedExceptionHandler {

    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<?> notAuthorizedExceptionHandler(){
        return ResponseEntity.status(401).build();
    }
}
