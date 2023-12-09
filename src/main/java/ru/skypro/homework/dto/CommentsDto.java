package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
public class CommentsDto {

    @Schema(description = "общее количество комментариев")
    @NotBlank
    private Integer count;


    @Schema(description = "#/components/schemas/Comment")
    @NotBlank
    private List<CommentDto> results;


}

