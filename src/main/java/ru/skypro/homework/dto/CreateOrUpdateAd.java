package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "создание или обновление объявления")
public class CreateOrUpdateAd {

    @Schema(description = "заголовок объявления", example = "Ноутбук")
    private String title;
    @Schema(description = "цена объявления", example = "54000")
    private Integer price;
    @Schema(description = "описание объявления", example = "Продается ноутбук")
    private String description;

}
