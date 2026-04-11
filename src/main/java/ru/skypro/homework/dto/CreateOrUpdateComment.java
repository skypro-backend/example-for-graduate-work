package ru.skypro.homework.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * DTO-класс для создания или обновления комментария.
 * Включает текст комментария с проверками на заполненность и длину.
 */
public class CreateOrUpdateComment {
    @NotBlank
    @Size(min = 8, max = 64)
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
