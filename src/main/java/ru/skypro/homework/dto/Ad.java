package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ad {

    @Schema(description = "Уникальный идентификатор автора", example = "100", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer author;

    @Schema(description = "Ссылка на изображение объявления", example = "https://site.com/img/1.jpg")
    private String image;

    @Schema(description = "Уникальный идентификатор объявления", example = "55", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer pk;

    @Schema(description = "Цена объявления в рублях", example = "15000", minimum = "0")
    private Integer price;

    @Schema(description = "Заголовок объявления", example = "Продам велосипед", maxLength = 100, minLength = 3)
    private String title;
}
