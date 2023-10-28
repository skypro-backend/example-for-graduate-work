package ru.skypro.homework.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentInconsistencyToAdException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Не найдено соответствия между айди комментария и объявления!";
    }
}
