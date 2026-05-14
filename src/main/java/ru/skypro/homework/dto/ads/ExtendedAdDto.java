package ru.skypro.homework.dto.ads;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Расширенная информация об объявлении")
public class ExtendedAdDto {

    @Schema(description = "id объявления", example = "100")
    private int pk;

    @Schema(description = "имя автора объявления", example = "John")
    private String authorFirstName;

    @Schema(description = "фамилия автора объявления", example = "Doe")
    private String authorLastName;

    @Schema(description = "описание объявления", example = "Отличное состояние, полный комплект")
    private String description;

    @Schema(description = "логин автора объявления", example = "john_doe@example.com")
    private String email;

    @Schema(description = "ссылка на картинку объявления", example = "/images/ads/100.jpg")
    private String image;

    @Schema(description = "телефон автора объявления", example = "+7 123 456-78-90")
    private String phone;

    @Schema(description = "цена объявления", example = "50000")
    private int price;

    @Schema(description = "заголовок объявления", example = "Продам iPhone 13")
    private String title;
}