package ru.skypro.homework.exception;

public class ImageNotFoundException extends NotFoundException {
    public ImageNotFoundException() {
        super("Картинка не найдена");
    }

}
