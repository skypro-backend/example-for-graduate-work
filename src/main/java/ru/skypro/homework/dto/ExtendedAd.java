package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Расширенная информация об объявлении")
public class ExtendedAd {
    @Schema(description = "id объявления", example = "1")
    private Integer pk;

    @Schema(description = "имя автора", example = "Иван")
    private String authorFirstName;

    @Schema(description = "фамилия автора", example = "Иванов")
    private String authorLastName;

    @Schema(description = "описание объявления", example = "Хороший телефон, не битый")
    private String description;

    @Schema(description = "логин автора", example = "user@mail.ru")
    private String email;

    @Schema(description = "ссылка на картинку", example = "/images/1.jpg")
    private String image;

    @Schema(description = "телефон автора", example = "+7 (999) 123-45-67")
    private String phone;

    @Schema(description = "цена объявления", example = "1000")
    private Integer price;

    @Schema(description = "заголовок объявления", example = "Продам телефон")
    private String title;
}