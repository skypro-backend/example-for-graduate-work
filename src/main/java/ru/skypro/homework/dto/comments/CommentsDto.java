package ru.skypro.homework.dto.comments;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Список комментариев")
public record CommentsDto(
        @Schema(description = "общее количество комментариев") Integer count,
        @Schema(description = "комментарии")List<CommentDto> results
        ) {
}
