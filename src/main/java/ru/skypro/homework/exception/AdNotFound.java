package ru.skypro.homework.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.security.logger.FormLogInfo;

@Slf4j
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Объявление не найдено")
public class AdNotFound extends RuntimeException {
    public AdNotFound() {
        log.info(FormLogInfo.getException());
    }

    public AdNotFound(String message) {
        super(message);
    }
}
