package ru.skypro.homework.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.skypro.homework.exception.PaymentRequiredException;

@ControllerAdvice
public class PaymentRequiredExceptionHandler {

    @ExceptionHandler(PaymentRequiredException.class)
    public ResponseEntity<?> paymentRequiredException(){
        return ResponseEntity.status(402).build();
    }
}
