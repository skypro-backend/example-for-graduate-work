package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommentsDto {
    Integer count;
    List<CommentDto> results;

    public CommentsDto(int size, List<CommentDto> commentsDtoList) {
    }

    public CommentsDto() {
    }
}
