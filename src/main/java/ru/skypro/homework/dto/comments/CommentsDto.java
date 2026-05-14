package ru.skypro.homework.dto.comments;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Список комментариев")
public class CommentsDto {

    @Schema(description = "общее количество комментариев", example = "10")
    private int count;

    @Schema(description = "список комментариев")
    private List<CommentDto> results;
}