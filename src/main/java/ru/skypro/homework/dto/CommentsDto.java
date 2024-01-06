package ru.skypro.homework.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CommentsDto {
    private int count;
    private List<CommentDto> results;

    public CommentsDto(int size, List<CommentDto> comments) {
    }
}
