package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Доступ к комменатрию невозможен.")
public class NoAccessToCommentException extends RuntimeException {

    public NoAccessToCommentException() {
        super("Доступ запрещен!");
    }
}
