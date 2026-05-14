package ru.skypro.homework.dto.comments;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@Schema(description = "Данные для создания или обновления комментария")
public class CreateOrUpdateCommentDto {

    @Schema(description = "текст комментария", minLength = 8, maxLength = 64, example = "Отличное объявление!")
    @Size(min = 8, max = 64)
    private String text;
}