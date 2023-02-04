package ru.skypro.homework.model.dto;

import lombok.Data;

import java.util.List;
@Data
public class ResponseWrapperCommentDto {
    private Integer count;
    private List<CommentDto> results;
}
