package ru.skypro.homework.exception;

/**
 * Выбрасывается при попытке изменить
 * чужой ресурс без прав администратора.
 */
public class AccessDeniedException
        extends RuntimeException {

    public AccessDeniedException(String message) {
        super(message);
    }
}
