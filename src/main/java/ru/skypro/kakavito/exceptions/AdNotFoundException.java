package ru.skypro.kakavito.exceptions;

/**
 * Создание исключения для отметки, что объявление не найдено
 */
public class AdNotFoundException extends RuntimeException {
    public AdNotFoundException(String message) {
        super(message);
    }
}
