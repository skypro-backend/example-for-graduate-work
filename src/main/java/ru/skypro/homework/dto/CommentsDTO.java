package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentsDTO {
    Integer count;
    CommentDTO results = new CommentDTO();
}
