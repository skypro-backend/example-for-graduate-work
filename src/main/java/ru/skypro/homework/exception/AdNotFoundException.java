package ru.skypro.homework.exception;

public class AdNotFoundException extends Exception{

    public AdNotFoundException(){
        super("Такого обьявления не существует");
    }
}
