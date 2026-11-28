package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Информация о комментарии")
public class Comment {
    @Schema(description = "id автора комментария", example = "1")
    private Integer author;

    @Schema(description = "ссылка на аватар автора", example = "/images/1.jpg")
    private String authorImage;

    @Schema(description = "имя создателя комментария", example = "Иван")
    private String authorFirstName;

    @Schema(description = "дата и время создания в миллисекундах", example = "1600000000000")
    private Long createdAt;

    @Schema(description = "id комментария", example = "1")
    private Integer pk;

    @Schema(description = "текст комментария", example = "Отличный телефон!")
    private String text;
}