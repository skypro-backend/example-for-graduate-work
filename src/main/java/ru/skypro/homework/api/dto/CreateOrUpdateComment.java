package ru.skypro.homework.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Данные для создания или обновления комментария")
public class CreateOrUpdateComment {

    @Schema(description = "текст комментария", minLength = 8, maxLength = 64, required = true)
    @NotBlank
    @Size(min = 8, max = 64)
    private String text;
}
