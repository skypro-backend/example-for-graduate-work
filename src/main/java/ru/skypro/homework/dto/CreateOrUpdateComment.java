package ru.skypro.homework.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@Schema(description = "данные для создания или обновления комментария")
public class CreateOrUpdateComment {

    @Schema(description = "текст комментария")
    @Size(min = 8, max = 64)
    private String text;
}