package ru.skypro.homework.dto.Ads;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные для создания или обновления объявления")
public class CreateOrUpdateAdDto {

    @Schema(description = "Заголовок объявления", example = "iPhone 15 Pro", minLength = 4, maxLength = 100)
    private String title;

    @Schema(description = "Цена товара в рублях", example = "99990", minimum = "0")
    private Integer price;

    @Schema(description = "Описание товара", example = "Новый в упаковке, гарантия", maxLength = 1000)
    private String description;
}
