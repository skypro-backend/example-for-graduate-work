package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * эксепш - класс
 * {@link CustomExceptionHandler#handleInvalidTraceIdException(IFElementExist)}
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class SecurityAccessException extends RuntimeException{

    public SecurityAccessException() {
    }

    public SecurityAccessException(String message) {
        super(message);
    }
}
