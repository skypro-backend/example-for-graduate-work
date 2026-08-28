package ru.skypro.homework.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * DTO для списка комментариев.
 * Используется для ответов на запросы, возвращающие коллекцию комментариев.
 * Основные функции:
 * - хранение общего количества комментариев;
 * - хранение списка комментариев.
 */
@Data
@Schema(description = "Список комментариев")
public class Comments {

    @Schema(description = "Общее количество комментариев")
    private Integer count;

    @Schema(description = "Список комментариев")
    private List<Comment> results;
}
