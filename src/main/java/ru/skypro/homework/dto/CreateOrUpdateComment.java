package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Schema(description = "Создание или обновление комментария")
public class CreateOrUpdateComment {
    @NotBlank
    @Size(min = 8, max = 64)
    @Schema(description = "текст комментария", minLength = 8, maxLength = 64, required = true)
    private String text;
}
