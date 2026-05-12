package ru.skypro.homework.exception;

/**
 * Исключение, выбрасываемое при отсутствии сущности в БД
 */
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}