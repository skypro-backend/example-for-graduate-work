package ru.skypro.homework.exception.exeption_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.skypro.homework.exception.custom_exception.AdNotFoundException;
import ru.skypro.homework.exception.custom_exception.UserNotFoundException;

@RestControllerAdvice
public class AppExceptionHandler{
    @ExceptionHandler({AdNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<? extends Exception> handleNotFoundExceptions(Exception exception){
        exception.printStackTrace();
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<? extends Exception> handleAccessExceptions(Exception exception){
        exception.printStackTrace();
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler()
    public ResponseEntity<? extends Exception> handleAnotherException(Exception exception){
        exception.printStackTrace();
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
