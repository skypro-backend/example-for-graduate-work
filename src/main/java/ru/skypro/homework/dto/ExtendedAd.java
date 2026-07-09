package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "полное описание объявления")
public class ExtendedAd {
    @Schema(description = "id объявления", example = "1")
    private Integer pk;
    @Schema(description = "имя автора объявления", example = "Ольга")
    private String authorFirstName;
    @Schema(description = "фамилия автора объявления", example = "Прохорова")
    private String authorLastName;
    @Schema(description = "описание объявления", example = "Продается ноутбук")
    private String description;
    @Schema(description = "логин автора объявления", example = "Olga123@yandex.ru")
    private String email;
    @Schema(description = "ссылка на картинку объявления")
    private String image;
    @Schema(description = "телефон автора объявления", example = "89051234567")
    private String phone;
    @Schema(description = "цена объявления", example = "54000")
    private Integer price;
    @Schema(description = "заголовок объявления", example = "Ноутбук")
    private String title;
}
