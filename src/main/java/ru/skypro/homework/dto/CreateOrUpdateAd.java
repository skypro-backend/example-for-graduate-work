package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Создание или обновление объявления")
public class CreateOrUpdateAd {

    @Schema(description = "Заголовок объявления", example = "Кухонный гарнитур из дуба")
    private String title;

    @Schema(description = "Цена объявления", example = "5000")
    private int price;

    @Schema(description = "Описание объявления", example = "Превосходное качество, массив дерева")
    private String description;
}