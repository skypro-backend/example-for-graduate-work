package ru.skypro.homework.Exceptions;

import ru.skypro.homework.Exceptions.NotFoundException;

public class AdNotFoundException extends NotFoundException {
    public AdNotFoundException() {
        super("Объявление не найдено");
    }
}