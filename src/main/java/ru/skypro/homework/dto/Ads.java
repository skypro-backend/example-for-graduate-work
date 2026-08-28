package ru.skypro.homework.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * DTO для списка объявлений.
 * Используется для ответов на запросы, возвращающие коллекцию объявлений.
 * Основные функции:
 * - хранение общего количества объявлений;
 * - хранение списка объявлений.
 */
@Data
@Schema(description = "Список объявлений")
public class Ads {

    @Schema(description = "Общее количество объявлений")
    private Integer count;

    @Schema(description = "Список объявлений")
    private List<Ad> results;

}
