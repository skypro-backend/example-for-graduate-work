package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Данные для передачи краткой информации об объявлении.
 * Используется при выводе списков объявлений и в ответах на базовые CRUD-операции.
 */
@Data
@Schema(description = "Краткая информация об объявлении")
public class AdDto {
    @Schema(description = "id автора объявления")
    private Integer author;

    @Schema(description = "Ссылка на картинку объявления")
    private String image;

    @Schema(description = "id объявления")
    private Integer pk;

    @Schema(description = "Цена объявления")
    private Integer price;

    @Schema(description = "Заголовок объявления")
    private String title;
}