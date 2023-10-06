package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.skypro.homework.exception.custom_exception.AdNotFoundException;
import ru.skypro.homework.exception.custom_exception.UserNotFoundException;

@RestControllerAdvice
public class AppExceptionHandler{
    @ExceptionHandler({AdNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<? extends Exception> handleNotFoundException(Exception exception){
        exception.printStackTrace();
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler()
    public ResponseEntity<? extends Exception> handleAnotherException(Exception exception){ // TODO
        exception.printStackTrace();
        return new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
    }
}
