package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "краткое описание объявления")
public class Ad {
    @Schema(description = "id автора объявления", example = "1")
    private Integer author;
    @Schema(description = "ссылка на картинку объявления")
    private String image;
    @Schema(description = "id объявления", example = "1")
    private Integer pk;
    @Schema(description = "цена объявления", example = "5000")
    private Integer price;
    @Schema(description = "заголовок объявления", example = "ноутбук")
    private String title;

}
