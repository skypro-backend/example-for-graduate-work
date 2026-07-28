package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Полная информация об объявлении")
public class ExtendedAd {

    @Schema(description = "ID объявления", example = "10")
    private Integer pk;

    @Schema(description = "Имя автора", example = "Иван")
    private String authorFirstName;

    @Schema(description = "Фамилия автора", example = "Иванов")
    private String authorLastName;

    @Schema(description = "Описание объявления", example = "Телефон в отличном состоянии")
    private String description;

    @Schema(description = "Email автора", example = "user@mail.ru")
    private String email;

    @Schema(description = "Ссылка на изображение", example = "/images/ad.jpg")
    private String image;

    @Schema(description = "Телефон автора", example = "+7 (999) 123-45-67")
    private String phone;

    @Schema(description = "Цена объявления", example = "100000")
    private Integer price;

    @Schema(description = "Заголовок объявления", example = "iPhone 15 Pro")
    private String title;
}
