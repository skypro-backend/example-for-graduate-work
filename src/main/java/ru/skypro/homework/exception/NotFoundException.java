package ru.skypro.homework.exception;

/**
 * Исключение, возникающее, когда запрашиваемый ресурс не найден.
 * Используется для:
 * - отсутствующих сущностей в базе данных,
 * - несуществующих объявлений, пользователей и комментариев.
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
