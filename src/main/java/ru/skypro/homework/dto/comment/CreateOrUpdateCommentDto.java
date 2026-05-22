package ru.skypro.homework.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные для создания комментария")
public class CreateOrUpdateCommentDto {

    @Schema(description = "Текст комментария", example = "Отличное объявление!", required = true, minLength = 1)
    private String text;
}
