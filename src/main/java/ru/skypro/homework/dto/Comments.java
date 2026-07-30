package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Список комментариев с общим количеством элементов.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comments {

    private Integer count;

    private List<Comment> results;
}
