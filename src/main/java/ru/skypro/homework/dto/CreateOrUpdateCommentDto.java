package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные для создания/обновления комментария")
public class CreateOrUpdateCommentDto {

    @Schema(description = "Текст комментария", example = "Отличное объявление!")
    private String text;
}
