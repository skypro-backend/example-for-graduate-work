package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Глобальный обработчик исключений для всего приложения.
 * <p>
 * Перехватывает критические ошибки безопасности, валидации и бизнес-логики,
 * возникающие на уровне сервисов или контроллеров, и конвертирует их
 * в понятные фронтенду HTTP-статусы без вывода системного трассировочного стека (stack trace).
 */
@RestControllerAdvice
public class ExceptionHandlerController {

    /**
     * Обрабатывает ошибки неверных учетных данных.
     * Возникает, если пользователь ввел неправильный текущий пароль при попытке его смены.
     *
     * @param e исключение {@link BadCredentialsException}
     * @return HTTP-статус {@code 400 Bad Request}
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> handleBadCredentials(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    /**
     * Обрабатывает ошибки нарушения прав доступа.
     * Возникает автоматически, когда SpEL-выражения в аннотациях {@code @PreAuthorize}
     * (методы проверки авторства {@code isAuthorOrAdmin}) возвращают {@code false}.
     *
     * @param e исключение {@link AccessDeniedException}
     * @return HTTP-статус {@code 403 Forbidden}
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Void> handleAccessDenied(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    /**
     * Обрабатывает некорректные аргументы, переданные в методы бизнес-логики.
     * Возникает, если в базе данных не найдены запрашиваемые сущности (пользователи, объявления, комментарии).
     *
     * @param e исключение {@link IllegalArgumentException}
     * @return HTTP-статус {@code 400 Bad Request} с текстовым описанием ошибки в теле ответа
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}