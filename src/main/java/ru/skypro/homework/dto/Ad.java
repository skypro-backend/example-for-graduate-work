package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Краткая информация об объявлении")
public class Ad {

    @Schema(description = "ID автора объявления", example = "1")
    private Integer author;

    @Schema(description = "Ссылка на изображение", example = "/images/ad.jpg")
    private String image;

    @Schema(description = "ID объявления", example = "10")
    private Integer pk;

    @Schema(description = "Цена объявления", example = "100000")
    private Integer price;

    @Schema(description = "Заголовок объявления", example = "iPhone 15 Pro")
    private String title;
}