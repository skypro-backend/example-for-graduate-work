package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Такой пользователь не найден")
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Пользователь не найден!");
    }
}
