package ru.skypro.homework.dto;
import javax.validation.constraints.NotEmpty;
import java.util.List;
public record CommentsDto(

        // общее количество комментариев
        Integer count,
        @NotEmpty
        List<CommentDto> results
) {
}
