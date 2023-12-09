package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema(description = "Расширение объявления")
public class ExtendedAdDto {

    @Schema(description = "id объявления")
    @NotBlank
    private Integer pk;

    @Schema(description = "имя автора объявления")
    @NotBlank
    private String authorFirstName;

    @Schema(description = "фамилия автора объявления")
    @NotBlank
    private String authorLastName;

    @Schema(description = "описание объявления")
    @NotBlank
    private String description;

    @Schema(description = "логин автора объявления")
    @NotBlank
    private String email;

    @Schema(description = "ссылка на картинку объявления")
    @NotBlank
    private String image;

    @Schema(description = "телефон автора объявления")
    @NotBlank
    private String phone;

    @Schema(description = "цена объявления")
    @NotBlank
    private Integer price;

    @Schema(description = "цена объявления")
    @NotBlank
    private String title;
}
