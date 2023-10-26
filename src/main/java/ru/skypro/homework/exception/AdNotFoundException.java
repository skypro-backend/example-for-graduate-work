package ru.skypro.homework.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@RequiredArgsConstructor
public class AdNotFoundException extends RuntimeException{
    private final Integer adId;

    @Override
    public String getMessage() {
        return "Объявление с id = " + adId + " не найдено!";
    }
}
