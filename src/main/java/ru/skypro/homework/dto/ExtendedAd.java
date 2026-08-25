package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Полная информация об объявлении")
public class ExtendedAd {

    @Schema(description = "id объявления", example = "1")
    private int pk;

    @Schema(description = "Имя автора объявления", example = "Антон")
    private String authorFirstName;

    @Schema(description = "Фамилия автора объявления", example = "Сопетов")
    private String authorLastName;

    @Schema(description = "Описание объявления", example = "Кастомная мебель под ключ")
    private String description;

    @Schema(description = "Логин автора объявления (email)", example = "sopetov@example.com")
    private String email;

    @Schema(description = "Ссылка на картинку объявления", example = "/images/1")
    private String image;

    @Schema(description = "Телефон автора объявления", example = "+79991112233")
    private String phone;

    @Schema(description = "Цена объявления", example = "5000")
    private int price;

    @Schema(description = "Заголовок объявления", example = "Дизайнерская кухня")
    private String title;
}