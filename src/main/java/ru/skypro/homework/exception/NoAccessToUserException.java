package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Доступ к пользователю запрещен! Введите корректный запрос.")
public class NoAccessToUserException extends RuntimeException {

    public NoAccessToUserException() {
        super("Доступ к пользователю запрещен!");
    }
}
