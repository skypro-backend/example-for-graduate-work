package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Информация о комментарии")
public class CommentDto {
    @Schema(description = "id автора комментария")
    private Integer author;

    @Schema(description = "Ссылка на аватар автора комментария")
    private String authorImage;

    @Schema(description = "Имя создателя комментария")
    private String authorFirstName;

    @Schema(description = "Дата и время создания комментария в миллисекундах")
    private Long createdAt;

    @Schema(description = "id комментария")
    private Integer pk;

    @Schema(description = "Текст комментария")
    private String text;
}