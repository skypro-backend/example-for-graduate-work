package ru.skypro.homework.comment;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Создание и редактирование комментариев")
public class CreateOrUpdateComment {

    @Schema(description = "Текст комментария", example = "привет. хочу посмотреть товар")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
