package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "список комментариев")
public class CommentsDto {

    @Schema(description = "количество комментариев")
    private int count;

    @Schema(description = "комментарии")
    private List<CommentDto> results;

}
