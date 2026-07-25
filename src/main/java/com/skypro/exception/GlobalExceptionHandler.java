package com.skypro.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.skypro.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * Глобальный обработчик исключений для REST API.
 * <p>
 * Перехватывает все исключения, возникающие в приложении, и преобразует их
 * в единообразные ответы с HTTP-статусом и понятным сообщением.
 * </p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Обрабатывает ошибку аутентификации (неверный логин или пароль).
     *
     * @param e исключение {@link BadCredentialsException}
     * @return ответ с HTTP-статусом 401 и сообщением об ошибке
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(BadCredentialsException e) {
        log.warn("Bad credentials: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse("Invalid username or password", HttpStatus.UNAUTHORIZED));
    }

    /**
     * Обрабатывает ошибку доступа (недостаточно прав для выполнения операции).
     *
     * @param e исключение {@link AccessDeniedException}
     * @return ответ с HTTP-статусом 403 и сообщением об ошибке
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(AccessDeniedException e) {
        log.warn("Access denied: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorResponse(e.getMessage(), HttpStatus.FORBIDDEN));
    }

    /**
     * Обрабатывает ситуацию, когда объявление не найдено.
     *
     * @param e исключение {@link AdNotFoundException}
     * @return ответ с HTTP-статусом 404 и сообщением об ошибке
     */
    @ExceptionHandler(AdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAdNotFound(AdNotFoundException e) {
        log.warn("Ad not found: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND));
    }

    /**
     * Обрабатывает ситуацию, когда комментарий не найден.
     *
     * @param e исключение {@link CommentNotFoundException}
     * @return ответ с HTTP-статусом 404 и сообщением об ошибке
     */
    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCommentNotFound(CommentNotFoundException e) {
        log.warn("Comment not found: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND));
    }

    /**
     * Обрабатывает ситуацию, когда пользователь не найден.
     *
     * @param e исключение {@link UserNotFoundException}
     * @return ответ с HTTP-статусом 404 и сообщением об ошибке
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException e) {
        log.warn("User not found: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND));
    }

    /**
     * Обрабатывает ситуацию, когда введён неверный старый пароль.
     *
     * @param e исключение {@link InvalidPasswordException}
     * @return ответ с HTTP-статусом 400 и сообщением об ошибке
     */
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPassword(InvalidPasswordException e) {
        log.warn("Invalid password: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    /**
     * Обрабатывает ошибку парсинга JSON (невалидный синтаксис).
     *
     * @param e исключение {@link JsonParseException}
     * @return ответ с HTTP-статусом 400 и сообщением об ошибке
     */
    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<ErrorResponse> handleJsonParse(JsonParseException e) {
        log.warn("Invalid JSON: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Invalid request data", HttpStatus.BAD_REQUEST));
    }

    /**
     * Обрабатывает ошибки валидации данных (например, @NotNull, @Size).
     *
     * @param e исключение {@link IllegalArgumentException}
     * @return ответ с HTTP-статусом 400 и сообщением об ошибке
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleBadRequest(IllegalArgumentException e) {
        log.warn("Bad request: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST));
    }

    /**
     * Обрабатывает ошибки валидации аргументов методов контроллеров.
     *
     * @param e исключение {@link MethodArgumentNotValidException}
     * @return ответ с HTTP-статусом 400 и сообщением об ошибке
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(message, HttpStatus.BAD_REQUEST));
    }

    /**
     * Обрабатывает все остальные непредвиденные ошибки.
     *
     * @param e исключение {@link Exception}
     * @return ответ с HTTP-статусом 500 и сообщением об ошибке
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception e) {
        log.error("Unexpected error", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR));
    }
}