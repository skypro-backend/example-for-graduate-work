package ru.skypro.homework.exceptions.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.exceptions.ImageNotFoundException;

@ControllerAdvice
public class ExceptionHandlerClass {

    @ExceptionHandler
    public <T extends Throwable> ResponseEntity<String> handleException(T e) {
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

        e.printStackTrace(System.err);
        return ResponseEntity.status(status).body(message);
    }


}