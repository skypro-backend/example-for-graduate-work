package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Комментарий к объявлению")
public class Comment {

    @Schema(description = "Идентификатор автора комментария")
    private Integer author;

    @Schema(description = "Ссылка на аватар автора комментария")
    private String authorImage;

    @Schema(description = "Имя автора комментария")
    private String authorFirstName;

    @Schema(description = "Дата и время создания комментария")
    private Long createdAt;

    @Schema(description = "Идентификатор комментария")
    private Integer pk;

    @Schema(description = "Текст комментария")
    private String text;
}