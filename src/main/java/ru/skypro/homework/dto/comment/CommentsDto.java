package ru.skypro.homework.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Schema(name = "Comments")
public class CommentsDto {

    @Schema(description = "общее количество комментариев")
    private Integer count;

    @Schema(description = "список комментариев")
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
