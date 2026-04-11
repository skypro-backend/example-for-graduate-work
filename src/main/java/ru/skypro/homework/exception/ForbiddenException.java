package ru.skypro.homework.exception;

import org.springframework.security.access.AccessDeniedException;

/**
 * Исключение, выбрасываемое, когда пользователь пытается
 * получить доступ к ресурсу, который ему не принадлежит.
 * Наследуется от AccessDeniedException для совместимости с Spring Security.
 */
public class ForbiddenException extends AccessDeniedException {

    public ForbiddenException(String msg) {
        super(msg);
    }
}
