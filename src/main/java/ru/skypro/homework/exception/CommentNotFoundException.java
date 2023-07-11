package ru.skypro.homework.exception;

public class CommentNotFoundException extends Exception{

    public CommentNotFoundException(){
        super("Такого комментария не существует");
    }
}
