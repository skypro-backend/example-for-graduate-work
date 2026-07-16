package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Данные для создания/обновления объявления")
public class CreateOrUpdateAdDto {

    @Schema(description = "Заголовок объявления", example = "Продам гараж")
    private String title;

    @Schema(description = "Цена объявления", example = "15000")
    private Integer price;

    @Schema(description = "Описание объявления", example = "Гараж в хорошем состоянии, сухой")
    private String description;
}
