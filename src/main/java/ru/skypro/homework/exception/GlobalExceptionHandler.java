package ru.skypro.homework.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

/**
 * Глобальный обработчик исключений для всего приложения.
 * Перехватывает и обрабатывает исключения, возникающие в контроллерах,
 * и возвращает соответствующие HTTP-ответы.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Обрабатывает исключение, возникающее при неверных учетных данных.
     *
     * @param e исключение BadCredentialsException
     * @return ResponseEntity с кодом 401 Unauthorized
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException e) {
        log.warn("Authentication failed: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    /**
     * Обрабатывает исключение, возникающее при отсутствии прав доступа.
     *
     * @param e исключение AccessDeniedException
     * @return ResponseEntity с кодом 403 Forbidden
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e) {
        log.warn("Access denied: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    /**
     * Обрабатывает исключение, возникающее при неверных аргументах.
     *
     * @param e исключение IllegalArgumentException
     * @return ResponseEntity с кодом 400 Bad Request
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn("Illegal argument: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Некорректные данные запроса: " + e.getMessage());
    }

    /**
     * Обрабатывает исключение, возникающее при ошибках парсинга JSON.
     *
     * @param e исключение JsonProcessingException или HttpMessageNotReadableException
     * @return ResponseEntity с кодом 400 Bad Request
     */
    @ExceptionHandler({JsonProcessingException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<?> handleJsonProcessingException(Exception e) {
        log.warn("Invalid JSON format: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Неверный формат JSON: " + e.getMessage());
    }

    /**
     * Обрабатывает исключение, возникающее при ошибках ввода-вывода.
     *
     * @param e исключение IOException
     * @return ResponseEntity с кодом 500 Internal Server Error
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handleIOException(IOException e) {
        log.error("IO error: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ошибка при обработке файла: " + e.getMessage());
    }

    /**
     * Обрабатывает исключение, возникающее при ошибках хранения файлов.
     *
     * @param e исключение StorageException
     * @return ResponseEntity с кодом 500 Internal Server Error
     */
    @ExceptionHandler(StorageException.class)
    public ResponseEntity<?> handleStorageException(StorageException e) {
        log.error("Storage error: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ошибка при сохранении файла: " + e.getMessage());
    }

    /**
     * Обрабатывает исключение, когда объект не найден.
     *
     * @param e исключение NotFoundException
     * @return ResponseEntity с кодом 404 Not Found
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        log.warn("Not found: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    /**
     * Обрабатывает исключение, возникающее при ошибках базы данных.
     *
     * @param e исключение DatabaseException
     * @return ResponseEntity с кодом 500 Internal Server Error
     */
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<?> handleDatabaseException(DatabaseException e) {
        log.error("Database error: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ошибка базы данных: " + e.getMessage());
    }

    /**
     * Обрабатывает исключение, возникающее при ошибках маппинга.
     *
     * @param e исключение MappingException
     * @return ResponseEntity с кодом 500 Internal Server Error
     */
    @ExceptionHandler(MappingException.class)
    public ResponseEntity<?> handleMappingException(MappingException e) {
        log.error("Mapping error: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ошибка при преобразовании данных: " + e.getMessage());
    }

    /**
     * Обрабатывает все остальные исключения времени выполнения.
     *
     * @param e исключение RuntimeException
     * @return ResponseEntity с кодом 500 Internal Server Error
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException e) {
        log.error("Unexpected runtime error: {}", e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Внутренняя ошибка сервера: " + e.getMessage());
    }
}