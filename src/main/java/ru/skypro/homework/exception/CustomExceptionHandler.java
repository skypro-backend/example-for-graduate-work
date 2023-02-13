package ru.skypro.homework.exception;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNullApi;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;

/**
 * Контроллер для всех эксепш
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * эксепш, если элемента нет в базе
     */
    @ExceptionHandler(ElemNotFound.class)
    public final ResponseEntity<ErrorResponse> handleUserNotFoundException(ElemNotFound ex) {
        String incorrectRequest = "Такого элемента нет";
        ErrorResponse error = new ErrorResponse(incorrectRequest,ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Отловка спринга
     */
    @Hidden
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidTraceIdException(MethodArgumentNotValidException ex) {
        String badRequest = "Какие-то данные были введены неправильно";
        ErrorResponse error = new ErrorResponse(badRequest,ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Отловка спринга
     */
    @Hidden
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidTraceIdException(MethodArgumentTypeMismatchException ex) {
        String badRequest = "Null ввести нельзя";
        ErrorResponse error = new ErrorResponse(badRequest,ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Отловка спринга
     */
    @Hidden
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidTraceIdException(ConstraintViolationException ex) {
        String badRequest = "Какие-то данные были введены неправильно";
        ErrorResponse error = new ErrorResponse(badRequest,ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @Hidden
    @ResponseStatus(HttpStatus.HTTP_VERSION_NOT_SUPPORTED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public final ResponseEntity<ErrorResponse> handleInvalidTraceIdException(HttpRequestMethodNotSupportedException ex) {
        String badRequest = "Какие-то данные были введены неправильно, либо метод не GET";
        ErrorResponse error = new ErrorResponse(badRequest,ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * эксепш, если элемент уже есть в базе
     */
    @ExceptionHandler(IFElementExist.class)
    public final ResponseEntity<ErrorResponse> handleInvalidTraceIdException(IFElementExist ex) {
        String badRequest = "Элемент уже есть в базе";
        ErrorResponse error = new ErrorResponse(badRequest,ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}