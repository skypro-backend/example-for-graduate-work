package ru.skypro.homework.ExceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.skypro.homework.exceptions.AccessErrorException;
import ru.skypro.homework.exceptions.CommentNotFoundException;

@ControllerAdvice
public class CommentExceptionHandler {

    @ExceptionHandler(value = {CommentNotFoundException.class})
    public ResponseEntity<?> handleCommentNotFound(CommentNotFoundException exception) {
        String message = "Комментарий не найден";
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AccessErrorException.class})
    public ResponseEntity<?> handleAccessError(AccessErrorException exception) {
        String message = "Недостаточно прав";
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }
}