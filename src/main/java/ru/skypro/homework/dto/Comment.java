package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Комментарий")
public class Comment {

    @Schema(description = "Id автора комментария", example = "1")
    private Integer author;

    @Schema(description = "Ссылка на аватар автора комментария", example = "/users/image/1")
    private String authorImage;

    @Schema(description = "Имя автора комментария", example = "Ivan")
    private String authorFirstName;

    @Schema(description = "Дата и время создания в миллисекундах", example = "1720000000000")
    private Long createdAt;

    @Schema(description = "Id комментария", example = "15")
    private Integer pk;

    @Schema(description = "Текст комментария", example = "Очень интересное объявление")
    private String text;
}