package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.FORBIDDEN)
public class StorageException extends RuntimeException{
    public StorageException(String message) {
        super(message);
    }
}
