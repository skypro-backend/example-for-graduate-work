package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Доступ к пользователю запрещен.")
public class NoAccessToUserException extends RuntimeException {

    public NoAccessToUserException() {
        super("ПДоступ к пользователю запреще!");
    }
}
