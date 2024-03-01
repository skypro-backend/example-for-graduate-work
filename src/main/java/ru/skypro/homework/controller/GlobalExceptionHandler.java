package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.skypro.homework.dto.ErrorMessage;
import ru.skypro.homework.exception.ImageReadException;
import ru.skypro.homework.exception.NotEnoughPermissionsException;
import ru.skypro.homework.exception.ResourceAlreadyExistsException;
import ru.skypro.homework.exception.ResourceNotFoundException;

import javax.xml.bind.ValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage handleDefaultException(Exception e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler({
            ValidationException.class,
            IllegalArgumentException.class,
            ResourceAlreadyExistsException.class,
            ImageReadException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessage handleBadArgumentException(RuntimeException e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessage handleNotFoundException(RuntimeException e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(NotEnoughPermissionsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ErrorMessage handleNotEnoughPermissionsException(RuntimeException e) {
        return new ErrorMessage(e.getMessage());
    }
}
