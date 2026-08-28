package ru.skypro.homework.exception;

/**
 * Исключение, возникающее когда объект не найден.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}