package ru.skypro.homework.Exceptions.NotFoundExpection;

public class CommentNotFoundException extends NotFoundException {

    public CommentNotFoundException() {
        super("Комментарий не найден");
    }
}