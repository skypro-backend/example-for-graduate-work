package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.skypro.homework.exception.EntityNotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * Глобальный обработчик исключений для всего приложения.
 * <p>
 * Перехватывает и обрабатывает все неперехваченные исключения, возникающие в контроллерах,
 * и возвращает пользовательские сообщения с соответствующими HTTP-статусами.
 * Обеспечивает единый формат ответов об ошибках для фронтенда.
 * <p>
 * Поддерживаемые типы исключений:
 * <ul>
 *   <li>Ошибки валидации ({@link MethodArgumentNotValidException}, {@link ConstraintViolationException})</li>
 *   <li>Некорректные параметры ({@link IllegalArgumentException})</li>
 *   <li>Проблемы аутентификации ({@link AuthenticationException}, {@link BadCredentialsException})</li>
 *   <li>Отказ в доступе ({@link AccessDeniedException})</li>
 *   <li>Объект не найден ({@link EntityNotFoundException})</li>
 *   <li>Любые другие непойманные ошибки (возвращается 500)</li>
 * </ul>
 * <p>
 * Использует {@link RestControllerAdvice} — значит, применяется ко всем контроллерам глобально.
 *
 * @see MethodArgumentNotValidException — автоматическая обработка ошибок валидации DTO
 * @see EntityNotFoundException — кастомное исключение при отсутствии сущности в БД
 * @see ResponseEntity — возврат структурированного тела ошибки
 */
@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    /**
     * Обрабатывает ошибки валидации полей DTO.
     * Вызывается при использовании аннотаций {@code @Valid} в контроллерах.
     *
     * @param ex исключение, содержащее результаты валидации
     * @return {@link ResponseEntity} с картой поле → сообщение об ошибке, статус 400
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Обрабатывает ошибки валидации на уровне отдельных полей (JSR-303).
     *
     * @param ex исключение, вызванное нарушением ограничений
     * @return {@link ResponseEntity} с картой свойство → сообщение, статус 400
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolation(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Обрабатывает исключения, связанные с некорректными аргументами методов.
     *
     * @param ex исключение "некорректный аргумент"
     * @return {@link ResponseEntity} с общим сообщением об ошибке, статус 400
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Некорректные параметры запроса");
        return ResponseEntity.badRequest().body(error);
    }

    /**
     * Обрабатывает отказ в доступе (например, при попытке изменить чужое объявление).
     *
     * @param ex исключение "доступ запрещён"
     * @return {@link ResponseEntity} с сообщением, статус 403
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDenied(AccessDeniedException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Доступ запрещён");
        return ResponseEntity.status(403).body(error);
    }

    /**
     * Обрабатывает общие ошибки аутентификации (например, пользователь не найден).
     *
     * @param ex исключение аутентификации
     * @return {@link ResponseEntity} с сообщением, статус 401
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> handleAuthException(AuthenticationException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Ошибка аутентификации");
        return ResponseEntity.status(401).body(error);
    }

    /**
     * Обрабатывает случай неверного пароля при входе.
     *
     * @param ex исключение "плохие учётные данные"
     * @return {@link ResponseEntity} с сообщением, статус 401
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleBadCredentials(BadCredentialsException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Текущий пароль неверен");
        return ResponseEntity.status(401).body(error);
    }

    /**
     * Обрабатывает случай, когда запрашиваемый объект (объявление, пользователь и т.д.) не найден.
     *
     * @param ex исключение "сущность не найдена"
     * @return {@link ResponseEntity} с сообщением, статус 404
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFound(EntityNotFoundException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Объект не найден");
        return ResponseEntity.status(404).body(error);
    }

    /**
     * Единый обработчик для всех остальных непойманных исключений.
     * <p>
     * Служит как fallback. Всегда возвращает 500 и логирует стек ошибки
     * для последующего анализа.
     *
     * @param ex любое необработанное исключение
     * @return {@link ResponseEntity} с сообщением "Внутренняя ошибка сервера", статус 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneral(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", "Внутренняя ошибка сервера");
        log.error("Unexpected error", ex);
        return ResponseEntity.status(500).body(error);
    }
}