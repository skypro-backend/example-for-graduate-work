package ru.skypro.kakavito.exceptions;

/**
 * Создание исключения для отметки, что комментарий не найден
 */
public class CommentNotFoundException extends RuntimeException {
    public CommentNotFoundException(String message) {
        super(message);
    }
}
