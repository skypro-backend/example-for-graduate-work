package ru.skypro.kakavito.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Создание ДТО
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsDTO {
    private Integer count;
    private List<CommentDTO> results;
}
