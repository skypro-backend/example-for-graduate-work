package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Полная информация об объявлении")
public class ExtendedAd {

    @Schema(description = "Id объявления", example = "10")
    private Integer pk;

    @Schema(description = "Имя автора объявления", example = "Ivan")
    private String authorFirstName;

    @Schema(description = "Фамилия автора объявления", example = "Ivanov")
    private String authorLastName;

    @Schema(description = "Описание объявления", example = "Почти новый, в хорошем состоянии")
    private String description;

    @Schema(description = "Логин автора объявления", example = "user@mail.com")
    private String email;

    @Schema(description = "Ссылка на картинку объявления", example = "/ads/image/10")
    private String image;

    @Schema(description = "Телефон автора объявления", example = "+7 999 123-45-67")
    private String phone;

    @Schema(description = "Цена объявления", example = "5000")
    private Integer price;

    @Schema(description = "Заголовок объявления", example = "Продам велосипед")
    private String title;
}