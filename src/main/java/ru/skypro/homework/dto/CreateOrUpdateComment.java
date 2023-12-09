package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Schema(description = "Добавление или обновление комментария")
public class CreateOrUpdateComment {
    @Schema(description = "текст комментария")
    @NotBlank
    @Size(min = 4, max = 100)
    private String text;

}
