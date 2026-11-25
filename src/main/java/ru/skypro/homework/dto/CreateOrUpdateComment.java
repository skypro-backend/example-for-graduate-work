package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Создание или обновление комментария")
public class CreateOrUpdateComment {
    @Schema(description = "текст комментария", minLength = 8, maxLength = 64, required = true)
    private String text;
}
