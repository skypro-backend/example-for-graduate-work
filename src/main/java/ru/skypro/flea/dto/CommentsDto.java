package ru.skypro.flea.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class CommentsDto {

    @Schema(description = "Total amount of comments")
    private Integer count;

    private List<CommentDto> results;

}
