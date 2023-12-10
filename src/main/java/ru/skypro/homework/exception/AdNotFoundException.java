package ru.skypro.homework.exception;

public class AdNotFoundException extends NotFoundException {
    public AdNotFoundException() {
        super("Объявление не найдено");
    }

}
