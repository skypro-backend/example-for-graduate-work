package ru.skypro.homework.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.skypro.homework.exception.notFound.*;


@ControllerAdvice
public class NotFoundExceptionHandler {

    @ExceptionHandler(RegisterReqNotFoundException.class)
    public ResponseEntity<?> registerNotFound(){
        return ResponseEntity.status(404).build();
    }

    @ExceptionHandler(AdsNotFoundException.class)
    public ResponseEntity<?> adsNotFound(){
        return ResponseEntity.status(404).build();
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<?> commentNotFound(){
        return ResponseEntity.status(404).build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFound(){
        return ResponseEntity.status(404).build();
    }

    @ExceptionHandler(LoginNotFoundException.class)
    public ResponseEntity<?> emailOrPasswordNotFound(){
        return ResponseEntity.status(404).build();
    }
}
