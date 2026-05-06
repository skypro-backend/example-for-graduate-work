package ru.skypro.homework.exception;

import lombok.Getter;

@Getter
public class ApiError {
    private String message;
    private int status;
    private long timestamp;

    public ApiError(String message, int status) {
        this.message = message;
        this.status = status;
        this.timestamp = System.currentTimeMillis();
    }
}
