package ru.skypro.homework.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Единая точка обработки исключений для всех контроллеров приложения.
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentials(BadCredentialsException e) {
        log.warn("Неверные учётные данные: {}", e.getMessage());
        return ResponseEntity.status(401).build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Void> handleAccessDenied(AccessDeniedException e) {
        log.warn("Отказано в доступе: {}", e.getMessage());
        return ResponseEntity.status(403).build();
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Void> handleNotFound(NotFoundException e) {
        log.warn("Ресурс не найден: {}", e.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> handleIllegalArgument(IllegalArgumentException e) {
        log.warn("Некорректные данные запроса: {}", e.getMessage());
        return ResponseEntity.badRequest().build();
    }
}
