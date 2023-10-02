package ru.skypro.homework.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentNotFoundException extends RuntimeException{

    public static final String DEFAULT_MESSAGE = "Комментарий не найден";

    public CommentNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
