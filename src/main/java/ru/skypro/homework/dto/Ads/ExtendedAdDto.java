package ru.skypro.homework.dto.Ads;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Полная информация об объявлении")
public class ExtendedAdDto {

    @Schema(description = "ID объявления", example = "123")
    private Long pk;

    @Schema(description = "Имя автора объявления", example = "Иван Петров")
    private String authorFirstName;

    @Schema(description = "Фамилия автора объявления", example = "Иванов")
    private String authorLastName;

    @Schema(description = "Описание объявления", example = "Ноутбук в отличном состоянии, 16GB RAM, 512GB SSD")
    private String description;

    @Schema(description = "Email автора", example = "ivan@example.com")
    private String email;

    @Schema(description = "URL изображения", example = "/images/ads/123.jpg")
    private String image;

    @Schema(description = "Телефон автора", example = "+79991234567")
    private String phone;

    @Schema(description = "Цена объявления", example = "50000")
    private Integer price;

    @Schema(description = "Заголовок объявления", example = "MacBook Pro 14\"")
    private String title;
}