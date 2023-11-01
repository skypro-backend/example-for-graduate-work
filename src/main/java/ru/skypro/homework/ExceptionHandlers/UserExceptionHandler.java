package ru.skypro.homework.ExceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.skypro.homework.exceptions.UserNotFoundException;
import ru.skypro.homework.exceptions.WrongCurrentPasswordException;
/**
 * The class for handling exception for UserController
 * @author Sulaeva Marina
 */
@ControllerAdvice
public class UserExceptionHandler {
    /**
     * The method for handling  WrongCurrentPasswordException
     */
    @ExceptionHandler(value = {WrongCurrentPasswordException.class})
    public ResponseEntity<?> handleWrongPassword(WrongCurrentPasswordException exception) {
        String message = "Введен некорректный текущий пароль";
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    /**
     * The method for handling  UserNotFoundException
     */
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<?> handleUserNotFound(UserNotFoundException exception) {
        String message = "Пользователь не найден";
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}