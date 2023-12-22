package ru.skypro.homework.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InCorrectPasswordException extends RuntimeException{
    public InCorrectPasswordException(String message){
        super(message);
    }
}
