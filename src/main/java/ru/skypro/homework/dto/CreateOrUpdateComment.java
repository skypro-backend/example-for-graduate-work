package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Данные для создания или обновления комментария")
public class CreateOrUpdateComment {

    @NotBlank
    @Size(min = 8, max = 64)
    @Schema(description = "Текст комментария", example = "Очень интересное объявление")
    private String text;
}