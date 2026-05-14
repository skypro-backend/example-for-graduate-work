package ru.skypro.homework.dto.ads;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Краткая информация об объявлении")
public class AdDto {

    @Schema(description = "id автора объявления", example = "1")
    private int author;

    @Schema(description = "ссылка на картинку объявления", example = "/images/ads/1.jpg")
    private String image;

    @Schema(description = "id объявления", example = "100")
    private int pk;

    @Schema(description = "цена объявления", example = "5000")
    private int price;

    @Schema(description = "заголовок объявления", example = "Продам iPhone")
    private String title;
}