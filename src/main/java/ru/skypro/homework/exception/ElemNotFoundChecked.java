package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.skypro.homework.loger.FormLogInfo;

/**
 * эксепш - класс {@link CustomExceptionHandler#handleInvalidTraceIdException(IFElementExist)}
 */
@ResponseStatus(HttpStatus.NOT_FOUND)

public class ElemNotFoundChecked extends Exception {

  public ElemNotFoundChecked() {

  }

  public ElemNotFoundChecked(String textMessage) {
    super("Exception: " + textMessage + FormLogInfo.getInfo());
    System.err.println("Exception: " + textMessage + FormLogInfo.getException());
  }
}

