package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Глобальный обработчик исключений приложения.
 * Обеспечивает единый формат обработки ошибок во всех контроллерах.
 * Перехватывает:
 * - бизнес-исключения,
 * - ошибки валидации,
 * - непредвиденные системные ошибки.
 * Возвращает стандартизированные HTTP-ответы с кодами ошибок.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Обработка исключения "ресурс не найден".
     * @param exception исключение NotFoundException
     * @return HTTP 404 с описанием ошибки
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(exception.getMessage(), 404));
    }

    /**
     * Обработка ошибки некорректного запроса.
     * @param exception исключение BadRequestException
     * @return HTTP 400 с сообщением об ошибке
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiError> handleBadRequest(BadRequestException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(exception.getMessage(), 400));
    }

    /**
     * Обработка ошибки работы с файловой системой.
     * @param exception исключение FileStorageException
     * @return HTTP 500 с сообщением об ошибке
     */
    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ApiError> handleFileStorage(FileStorageException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiError(exception.getMessage(), 500));
    }
}
