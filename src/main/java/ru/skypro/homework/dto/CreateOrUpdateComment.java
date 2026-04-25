package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "DTO для создания или обновления комментария")
public class CreateOrUpdateComment {
    @Schema(description = "Текст комментария", example = "Хорошее объявление!", minLength = 8, maxLength = 64)
    @NotBlank(message = "Текст комментария не может быть пустым")
    @Size(min = 8, max = 64, message = "Текст комментария должен содержать от 8 до 64 символов")
    private String text;
}