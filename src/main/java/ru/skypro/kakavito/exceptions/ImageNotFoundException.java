package ru.skypro.kakavito.exceptions;

/**
 * Создание исключения для отметки, что изображение не найдено
 */
public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(String message) {
        super(message);
    }
}
