package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Краткая информация об объявлении")
public class Ad {

    @Schema(description = "Идентификатор автора объявления")
    private Integer author;

    @Schema(description = "Ссылка на изображение объявления")
    private String image;

    @Schema(description = "Идентификатор объявления")
    private Integer pk;

    @Schema(description = "Цена объявления")
    private Integer price;

    @Schema(description = "Заголовок объявления")
    private String title;
}