package ru.skypro.homework.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.skypro.homework.exception.NotFoundException;

@RestControllerAdvice
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Void> handleException(NotFoundException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(e.getHttpStatus()).build();
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Void> handleException(Throwable e) {
        log.error("Неизвестная ошибка", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
