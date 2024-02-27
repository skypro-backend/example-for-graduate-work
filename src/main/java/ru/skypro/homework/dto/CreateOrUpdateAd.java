package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * CreateOrUpdateAd
 */
@Data
public class CreateOrUpdateAd {
    /**
     * Заголовок объявления
     */
    @Size(min = 4,max = 32)
    @Schema(description = "заголовок объявления")
    String title;
    /**
     * Цена объявления
     */
    @Min(0)
    @Max(10000000)
    @Schema(description = "цена объявления" )
    Integer price;
//    int price;
    /**
     * Описание объявления
     */
    @Size(min = 8,max = 64)
    @Schema(description = "описание объявления")
    String description;
}
