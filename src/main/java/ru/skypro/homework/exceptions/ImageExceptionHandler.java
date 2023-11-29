package ru.skypro.homework.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ImageExceptionHandler {
    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<?> handleNotFound(ImageNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Картинок не найдено!");
    }
}