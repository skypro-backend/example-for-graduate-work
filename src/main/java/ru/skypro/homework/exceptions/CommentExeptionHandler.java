package ru.skypro.homework.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommentExeptionHandler {
    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<?> handleNotFound(CommentNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Комментария нет!");
    }
}
