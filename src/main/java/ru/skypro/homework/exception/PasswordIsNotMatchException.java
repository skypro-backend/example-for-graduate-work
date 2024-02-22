package ru.skypro.homework.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * эксепш - класс
 */
@Slf4j
@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Пароль не совпал.")
public class PasswordIsNotMatchException extends RuntimeException {
    public PasswordIsNotMatchException() {
    }
}
