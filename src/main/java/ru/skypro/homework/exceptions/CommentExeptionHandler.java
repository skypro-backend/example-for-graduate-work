package ru.skypro.homework.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommentExeptionHandler {
    @ExceptionHandler(CommentNotFoundExeption.class)
    public ResponseEntity<?> handleNotFound(CommentNotFoundExeption e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Комментария нет!");
    }
}
