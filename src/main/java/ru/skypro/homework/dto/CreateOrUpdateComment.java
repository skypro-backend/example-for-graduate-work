package ru.skypro.homework.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Объект для создания или обновления комментария")
public class CreateOrUpdateComment {

    @Schema(description = "Текст комментария", required = true, minLength = 8, maxLength = 64)
    @NotBlank
    @Size(min = 8, max = 64)
    private String text;
}
