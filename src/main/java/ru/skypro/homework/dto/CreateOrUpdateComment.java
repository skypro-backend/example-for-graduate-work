package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Создание или обновление комментария")
public class CreateOrUpdateComment {

    @Schema(description = "Текст комментария", example = "Отличный мастер, кухня собрана идеально!")
    private String text;
}