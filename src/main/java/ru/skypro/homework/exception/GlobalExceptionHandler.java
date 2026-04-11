package ru.skypro.homework.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Глобальный обработчик исключений приложения.
 * Перехватывает исключения, например, ForbiddenException,
 * и возвращает структурированный ответ с кодом ошибки.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Обрабатывает исключение ForbiddenException и возвращает ответ
     * с кодом 403 (Forbidden) и информацией об ошибке.
     *
     * @param ex исключение ForbiddenException
     * @return ResponseEntity с телом ошибки и статусом 403
     */
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> handleForbidden(ForbiddenException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
}

/**
 * DTO для ответа при ошибке.
 * Включает статус, сообщение и дату/время ошибки.
 */
@Getter
class ErrorResponse {
    private final int status;
    private final String message;
    private final LocalDateTime timestamp;

    public ErrorResponse(int status, String message, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}