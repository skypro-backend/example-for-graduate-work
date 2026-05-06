package ru.skypro.homework.dto.response;

import lombok.Value;

import java.util.List;

@Value
public class CommentsResponseDto {
    Integer count;
    List<CommentResponseDto> results;
}
