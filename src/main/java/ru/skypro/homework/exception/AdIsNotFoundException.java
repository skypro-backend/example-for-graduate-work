package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdIsNotFoundException extends RuntimeException {
    public AdIsNotFoundException(String message) {
        super(message);
    }
}
