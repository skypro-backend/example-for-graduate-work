package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Входящие данные для публикации или редактирования текста комментария.
 * Накладывает ограничения на минимальную и максимальную длину вводимого отзыва.
 */
@Data
@Schema(description = "Данные для создания или обновления комментария")
public class CreateOrUpdateComment {
    @Schema(description = "Текст комментария", example = "Отличный товар, рекомендую!")
    @NotNull
    @Size(min = 8, max = 64)
    private String text;
}