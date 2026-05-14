package ru.skypro.homework.dto.comments;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Информация о комментарии")
public class CommentDto {

    @Schema(description = "id автора комментария", example = "5")
    private int author;

    @Schema(description = "ссылка на аватар автора комментария", example = "/images/avatar/5.jpg")
    private String authorImage;

    @Schema(description = "имя создателя комментария", example = "Jane")
    private String authorFirstName;

    @Schema(description = "дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970", example = "1634567890000")
    private long createdAt;

    @Schema(description = "id комментария", example = "42")
    private int pk;

    @Schema(description = "текст комментария", example = "Отличное объявление!")
    private String text;
}