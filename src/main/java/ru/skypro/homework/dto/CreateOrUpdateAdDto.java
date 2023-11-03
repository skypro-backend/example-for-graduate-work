package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "сохранение и обновление объявления")
public class CreateOrUpdateAdDto {

    @Schema(description = "Заголовок объявления")
    @NotBlank
    @Size(min = 4, max = 32)
    private String  title;

    @Schema(description = "Цена объявления")
    @NotBlank
    @Size(max = 10000000)
    private int price;

    @Schema(description = "Описание объявления")
    @NotBlank
    @Size(min = 4, max = 64)
    private String  descption;
}
