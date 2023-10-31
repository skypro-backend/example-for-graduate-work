package ru.skypro.homework.exceptions.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import ru.skypro.homework.exceptions.AdNotFoundException;
import ru.skypro.homework.exceptions.ImageNotFoundException;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerClass {

    @ExceptionHandler
    public ResponseEntity<String> handleException(Throwable e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = e.getMessage();

        if (e instanceof MaxUploadSizeExceededException) {
            status = HttpStatus.EXPECTATION_FAILED;
            message = "Превышен максимальный размер файла ";
        } else if (e instanceof ImageNotFoundException) {
            status = HttpStatus.NOT_FOUND;
            message = "Не найден Image по данному пути";
        } else if (e instanceof AdNotFoundException) {
            status = HttpStatus.NOT_FOUND;
            message = "Объявление не найдено";
        }

        log.error(message);
        return ResponseEntity.status(status).body(message);
    }


}