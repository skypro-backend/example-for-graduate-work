package ru.skypro.homework.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Доступ к объявлению невозможен. Проверьте правильность запроса.")
public class NoAccessToAdException  extends RuntimeException    {

    public NoAccessToAdException() {
        super("Доступ к объявлению запрещён!");
    }
}


