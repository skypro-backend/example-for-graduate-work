package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO списка комментариев.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrapperComment {

    private Integer count; // общее количество комментариев
    private List<CommentDto> results; // все комментарии
}
