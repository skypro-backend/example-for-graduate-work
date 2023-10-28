package ru.skypro.homework.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@RequiredArgsConstructor
public class CommentNotFoundException extends RuntimeException {
    private final Integer commentId;

    @Override
    public String getMessage() {
        return "Комментарий с id = " + commentId + " не найден!";
    }
}
