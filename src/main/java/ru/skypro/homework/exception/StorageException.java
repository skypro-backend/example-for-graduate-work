package ru.skypro.homework.exception;


/**
 * Исключение, возникающее при ошибках хранения файлов.
 */
public class StorageException extends RuntimeException {
    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
