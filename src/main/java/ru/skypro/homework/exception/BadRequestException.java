package ru.skypro.homework.exception;

/**
 * Исключение, возникающее при некорректном запросе пользователя.
 * Используется, когда пользователь передаёт:
 * - невалидные данные,
 * - нарушает бизнес-логику,
 * - пытается выполнить недопустимое действие.
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
