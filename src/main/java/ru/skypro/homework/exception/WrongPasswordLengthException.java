package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongPasswordLengthException extends RuntimeException{
    public WrongPasswordLengthException(String message) {
        super(message);
    }
}
