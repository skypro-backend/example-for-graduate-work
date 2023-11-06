package ru.skypro.homework.Exceptions.NotFoundExpection;

public class AdNotFoundException extends NotFoundException{
    public AdNotFoundException() {
        super("Объявление не найдено");
    }
}