package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrUpdateAd {

    @Schema(description = "Заголовок объявления. Обязательно для заполнения.", example = "Куплю гараж", minLength = 3, maxLength = 100, requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "Описание товара или услуги", example = "Состояние отличное, есть документы", maxLength = 500)
    private String description;

    @Schema(description = "Цена продажи", example = "250000", minimum = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer price;
}
