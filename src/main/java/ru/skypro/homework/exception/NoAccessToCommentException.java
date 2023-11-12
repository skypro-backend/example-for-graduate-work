package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Доступ к пользователб запрещен. Проверьте правильность запроса.")
public class NoAccessToCommentException extends RuntimeException {

    public NoAccessToCommentException() {
        super("Доступ к пользователб запрещен.");
    }
}
