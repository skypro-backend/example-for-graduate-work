package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class PasswordChangeException extends RuntimeException {

    public PasswordChangeException(String message) {
        super(message);
    }
}
