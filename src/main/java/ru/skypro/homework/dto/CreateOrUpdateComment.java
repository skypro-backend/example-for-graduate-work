package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "создание или обновление комментария")
public class CreateOrUpdateComment {
    @Schema(description = "текст комментария", example = "покупкой довольна")
    private String text;

}
