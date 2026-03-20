package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Краткая информация об объявлении")
public class Ad {

    @Schema(description = "Id автора объявления", example = "1")
    private Integer author;

    @Schema(description = "Ссылка на картинку объявления", example = "/ads/image/1")
    private String image;

    @Schema(description = "Id объявления", example = "10")
    private Integer pk;

    @Schema(description = "Цена объявления", example = "5000")
    private Integer price;

    @Schema(description = "Заголовок объявления", example = "Продам велосипед")
    private String title;
}