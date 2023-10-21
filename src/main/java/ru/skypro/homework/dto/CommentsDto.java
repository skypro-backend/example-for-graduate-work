package ru.skypro.homework.dto;
import java.util.List;
public record CommentsDto(
        /**
         общее количество комментариев
         */
        Integer count,
        List<CommentDto> results
) {
}
