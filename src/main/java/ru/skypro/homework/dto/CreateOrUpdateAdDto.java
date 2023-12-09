package ru.skypro.homework.dto;

import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "Создание или обновление объявления")
public class CreateOrUpdateAdDto {


    @Schema(description = "заголовок объявления")
    @Size(min = 4,max = 32)
    @NotBlank(message ="Введите заголовок объявления")
    private String title;

    @Schema(description = "цена объявления")
    @Size(min = 0,max = 10000000)
    @NotBlank(message ="Введите цену объявления")
    private Integer price;

    @Schema(description = "описание объявления")
    @Size(min = 8,max = 64)
    @NotBlank(message ="Введите описание объявления")
    private String description;
}
