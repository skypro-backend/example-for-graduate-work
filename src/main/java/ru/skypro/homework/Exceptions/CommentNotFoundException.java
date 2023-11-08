package ru.skypro.homework.Exceptions;

import ru.skypro.homework.Exceptions.NotFoundException;

public class CommentNotFoundException extends NotFoundException {

    public CommentNotFoundException() {
        super("Комментарий не найден");
    }
}