package ru.skypro.homework.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseWrapperCommentDTO {
    // Общее количество комментариев
    private Integer count;
    List<CommentDTO> results;
}
