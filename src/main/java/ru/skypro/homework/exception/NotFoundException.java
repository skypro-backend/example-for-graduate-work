package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;


public class NotFoundException extends RuntimeException {
    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    public NotFoundException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatus() {
        return HTTP_STATUS;
    }
}
