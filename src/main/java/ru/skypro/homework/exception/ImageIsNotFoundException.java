package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ImageIsNotFoundException extends RuntimeException {
    public ImageIsNotFoundException(String message) {
        super(message);
    }
}
