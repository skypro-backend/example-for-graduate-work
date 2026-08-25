package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Комментарий")
public class Comment {

    @Schema(description = "id автора комментария", example = "1")
    private int author;

    @Schema(description = "ссылка на аватарку автора комментария", example = "/images/avatars/1")
    private String authorImage;

    @Schema(description = "имя автора комментария", example = "Антон")
    private String authorFirstName;

    @Schema(description = "дата и время создания комментария в миллисекундах", example = "1620000000000")
    private long createdAt;

    @Schema(description = "id комментария", example = "1")
    private int pk;

    @Schema(description = "текст комментария", example = "Превосходная работа")
    private String text;
}