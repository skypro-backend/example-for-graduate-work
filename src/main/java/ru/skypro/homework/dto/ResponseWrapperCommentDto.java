package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class ResponseWrapperCommentDto {
    private int count;
    private CommentDto[] results;
}
