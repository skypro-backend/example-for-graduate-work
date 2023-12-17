package ru.skypro.homework.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

public class GetImageException extends RuntimeException {
    public GetImageException(String message) {
        super(message);
    }
}
