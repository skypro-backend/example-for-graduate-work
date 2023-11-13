package ru.skypro.homework.dto;

import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CommentsDto {
    private Integer count;
    private List<CommentDto> results;
}
