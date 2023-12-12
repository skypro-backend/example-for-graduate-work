package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CommentsDTO {
    private Integer count;
    private List<Comment> results;
}
