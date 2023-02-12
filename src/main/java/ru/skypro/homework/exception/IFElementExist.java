package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * эксепш - класс
 * {@link CustomExceptionHandler#handleInvalidTraceIdException(MethodArgumentNotValidException)}
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IFElementExist extends RuntimeException {
    public IFElementExist() {

    }
}
