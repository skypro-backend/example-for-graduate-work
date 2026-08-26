package ru.skypro.homework.exception;

/**
 * Исключение, возникающее при ошибках маппинга данных.
 */
public class MappingException extends RuntimeException {
    public MappingException(String message) {
        super(message);
    }

    public MappingException(String message, Throwable cause) {
        super(message, cause);
    }
}
