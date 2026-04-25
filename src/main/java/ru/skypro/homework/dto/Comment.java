package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Модель комментария")
public class Comment {
    @Schema(description = "ID автора комментария", example = "1")
    private Integer author;

    @Schema(description = "Ссылка на аватар автора комментария", example = "/images/user/1.jpg")
    private String authorImage;

    @Schema(description = "Имя автора комментария", example = "Иван")
    private String authorFirstName;

    @Schema(description = "Дата создания комментария в миллисекундах", example = "1700000000000")
    private Long createdAt;

    @Schema(description = "ID комментария", example = "1")
    private Integer pk;

    @Schema(description = "Текст комментария", example = "Отличное объявление!")
    private String text;
}