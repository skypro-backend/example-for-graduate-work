package ru.skypro.homework.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * DTO для создания или обновления комментария.
 * Используется в запросах POST /ads/{id}/comments и PATCH /ads/{id}/comments/{commentId}.
 * Содержит валидацию текста комментария.
 * Основные функции:
 * - передача текста нового комментария на сервер;
 * - обновление существующего комментария.
 */
@Data
@Schema(description = "Объект для создания или обновления комментария")
public class CreateOrUpdateComment {

    @Schema(description = "Текст комментария", required = true, minLength = 8, maxLength = 64)
    @NotBlank
    @Size(min = 8, max = 64)
    private String text;
}
