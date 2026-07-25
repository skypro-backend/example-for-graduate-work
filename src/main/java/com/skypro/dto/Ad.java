package com.skypro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для представления объявления в списке.
 * <p>
 * Используется в ответах на запросы получения всех объявлений и объявлений текущего пользователя.
 * Поле {@code pk} является идентификатором, поле {@code author} содержит ID автора.
 * </p>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ad {

    @Schema(description = "id объявления")
    private Integer pk;

    @Schema(description = "id автора объявления")
    private Integer author;

    @Schema(description = "цена объявления")
    private Integer price;

    @Schema(description = "заголовок объявления")
    private String title;

    @Schema(description = "ссылка на картинку объявления")
    private String image;
}
