package ru.skypro.homework.exception;

/**
 * Исключение, возникающее при ошибках работы с файловой системой.
 * Используется при:
 * - ошибках сохранения файлов,
 * - ошибках чтения изображений.
 */
public class FileStorageException extends RuntimeException {
    public FileStorageException(String message) {
        super(message);
    }
}
