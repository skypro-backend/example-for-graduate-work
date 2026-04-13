package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные для создания или обновления комментария")
public class CreateOrUpdateComment {

    @Schema(description = "текст комментария")
    @Size(min = 8, max = 64)
    private String text;
}
