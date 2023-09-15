package ru.skypro.homework.dto.comments.out;

import lombok.Data;


import java.util.List;

@Data
public class CommentsDto {
    private Integer count;
    private List<CommentDto> results;
}
