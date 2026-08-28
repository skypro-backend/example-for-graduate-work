package ru.skypro.homework.exception;

/**
 * Исключение, возникающее при ошибках базы данных.
 */
public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
