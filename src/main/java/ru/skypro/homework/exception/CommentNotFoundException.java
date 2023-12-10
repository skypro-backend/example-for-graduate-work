package ru.skypro.homework.exception;

public class CommentNotFoundException extends NotFoundException {
    public CommentNotFoundException() {
        super("Комментарий не найден");
    }

}
