package ru.skypro.homework.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.skypro.homework.security.logger.FormLogInfo;

/**
 * эксепш - класс
 */
@Slf4j
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Пользователь не найден.")
public class UserNotFound extends RuntimeException {
    public UserNotFound() {
        log.info(FormLogInfo.getException());
    }

    public UserNotFound(String message) {
        super(message);
    }
}