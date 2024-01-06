package ru.skypro.kakavito.dto;

import lombok.Data;

import java.util.List;

/**
 * Создание ДТО
 */
@Data
public class CommentsDTO {
    private Integer count;
    private List<CommentDTO> results;
}
