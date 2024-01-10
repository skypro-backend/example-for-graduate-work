package ru.skypro.kakavito.exceptions;

/**
 * Создание исключения для отметки, что пользователь не найден
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
