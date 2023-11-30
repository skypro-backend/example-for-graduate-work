package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PhotoAdNotFoundException extends RuntimeException{
    public PhotoAdNotFoundException() {
    }

    public PhotoAdNotFoundException(String message) {
        super(message);
    }

    public PhotoAdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhotoAdNotFoundException(Throwable cause) {
        super(cause);
    }

    public PhotoAdNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
