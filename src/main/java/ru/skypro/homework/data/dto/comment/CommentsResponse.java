package ru.skypro.homework.data.dto.comment;

import lombok.Data;

import java.util.List;

@Data
public class CommentsResponse {
    private Integer count;
    private List<CommentItemDto> results;
}
