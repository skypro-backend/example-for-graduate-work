package ru.skypro.homework.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "Пагинированный список комментариев")
public class CommentsDto {

    @Schema(description = "Общее количество комментариев", example = "15")
    private Integer count;

    @Schema(description = "Массив комментариев")
    private List<CommentDto> results = new ArrayList<>();

    public CommentsDto() {
        this.count = 0;
        this.results = new ArrayList<>();
    }

    public CommentsDto(Integer count, List<CommentDto> results) {
        this.count = count;
        this.results = results != null ? results : new ArrayList<>();
    }
}