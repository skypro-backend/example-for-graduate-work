package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO, используемое клиентом для передачи параметров создания или обновления комментария.
 */
@Data
@Schema(description = "Данные для создания или обновления комментария")
public class CreateOrUpdateComment {

    @Schema(
            description = "текст комментария",
            minLength = 8,
            maxLength = 64
    )
    private String text;
}