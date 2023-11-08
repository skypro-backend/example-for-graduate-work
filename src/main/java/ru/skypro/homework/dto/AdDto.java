package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@ToString
@AllArgsConstructor
@Schema(description = "Объявление")
public class AdDto {

    @Schema(description = "id объявления",
            accessMode = Schema.AccessMode.READ_ONLY)
    private int pk;

    @Schema(description = "id автора объявления")
    @NotEmpty
    private int author;

    @Schema(description = "ссылка на картинку объявления")
    private String image;


    @Schema(description = "цена объявления")
    private int price;

    @Schema(description = "заголовок объявления")
    @NotBlank
    private String title;
}
