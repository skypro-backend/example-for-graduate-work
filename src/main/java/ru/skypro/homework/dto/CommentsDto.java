package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Setter
@Getter
@ToString
@AllArgsConstructor
@Schema(description = "список комментариев")
public class CommentsDto {

    @Schema(description = "количество комментариев")
    private int count;

    @Schema(description = "комментарии")
    private List<CommentDto> results;

}
