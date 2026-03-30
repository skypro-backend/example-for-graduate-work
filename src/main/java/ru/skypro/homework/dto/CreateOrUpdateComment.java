package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Size;

@Schema(description = "Данные для создания или обновления комментария")
public class CreateOrUpdateComment {

    @Schema(description = "текст комментария",
            minLength = 8,
            maxLength = 64,
            required = true,
            example = "Отличное объявление!")
    @Size(min = 8, max = 64)
    private String text;

    public CreateOrUpdateComment(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
