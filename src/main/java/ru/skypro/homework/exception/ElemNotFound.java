package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * эксепш - класс
 * {@link CustomExceptionHandler#handleInvalidTraceIdException(IFElementExist)}
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElemNotFound extends RuntimeException {

  public ElemNotFound() {

  }

  public ElemNotFound(String textException) {

  }
}

