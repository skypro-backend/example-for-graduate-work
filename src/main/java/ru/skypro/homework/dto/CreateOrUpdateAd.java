package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * Входящие данные для создания нового или редактирования существующего объявления.
 * Содержит аннотации валидации текстовых полей и ценового диапазона.
 */
@Data
@Schema(description = "Данные для создания или обновления объявления")
public class CreateOrUpdateAd {
    @Schema(description = "Заголовок объявления", example = "Продам айфон 13")
    @Size(min = 4, max = 32)
    private String title;

    @Schema(description = "Цена объявления", example = "45000")
    @Min(0)
    @Max(10000000)
    private Integer price;

    @Schema(description = "Описание объявления", example = "В отличном состоянии, полный комплект")
    @Size(min = 8, max = 64)
    private String description;
}