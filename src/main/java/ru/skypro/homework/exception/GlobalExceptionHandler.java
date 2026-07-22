package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Объект не найден
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(
            ResourceNotFoundException e
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        Map.of(
                                "message",
                                e.getMessage()
                        )
                );
    }

    /**
     * Нет прав доступа (кастомная проверка владельца)
     */
    @ExceptionHandler(ru.skypro.homework.exception.AccessDeniedException.class)
    public ResponseEntity<Void> handleCustomAccessDenied(
            ru.skypro.homework.exception.AccessDeniedException e
    ) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .build();
    }

    /**
     * Нет прав доступа Spring Security
     */
    @ExceptionHandler(
            org.springframework.security.access.AccessDeniedException.class
    )
    public ResponseEntity<Void> handleSecurityAccessDenied(
            org.springframework.security.access.AccessDeniedException e
    ) {

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .build();
    }

    /**
     * Ошибка запроса
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(
            IllegalArgumentException e
    ) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        Map.of(
                                "message",
                                e.getMessage()
                        )
                );
    }

    /**
     * Неверная авторизация
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentials(
            BadCredentialsException e
    ) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }
    /**
     * Ошибка сервера
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(
            RuntimeException e
    ) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        Map.of(
                                "message",
                                e.getMessage()
                        )
                );
    }
}