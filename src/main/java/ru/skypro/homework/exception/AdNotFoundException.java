package ru.skypro.homework.exception;

public class AdNotFoundException extends Exception{
    public AdNotFoundException() {
        super("Такого объявления не существует");
    }
}
