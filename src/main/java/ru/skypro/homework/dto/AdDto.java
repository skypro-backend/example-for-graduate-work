package ru.skypro.homework.dto;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "Объявление ")
public class AdDto {

    @Schema(description = "id автора объявления ")
    @NotBlank
    private Integer author;

    @Schema(description = "ссылка на картинку объявления ")
    @NotBlank
    private String image;

    @Schema(description = "id объявления ")
    @NotBlank
    private Integer pk;

    @Schema(description = "цена объявления ")
    @NotBlank
    private Integer price;

    @Schema(description = "заголовок объявления ")
    @NotBlank
    private String title;
}
