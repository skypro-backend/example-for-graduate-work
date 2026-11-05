package ru.skypro.homework.dto.comments;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "информация о комментарии")
public record CommentDto(
        @Schema(description = "id автора комментария") Integer author,
        @Schema(description = "ссылка на аватар автора комментария") String authorImage,
        @Schema(description = "имя создателя комментария") String authorFirstName,
        @Schema(description = "дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970") Long createdAt,
        @Schema(description = "id комментария") Integer pk,
        @Schema(description = "текст комментария") String text
) {
}
