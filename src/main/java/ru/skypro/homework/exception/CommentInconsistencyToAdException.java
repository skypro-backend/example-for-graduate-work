package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CommentInconsistencyToAdException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Этот комментарий был написан для другого объявления!";
    }
}
