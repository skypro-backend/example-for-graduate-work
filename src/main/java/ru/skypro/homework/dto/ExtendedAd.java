package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Расширенная информация об объявлении")
public class ExtendedAd {
    @Schema(description = "ID объявления", example = "1")
    private Integer pk;

    @Schema(description = "Имя автора объявления", example = "Иван")
    private String authorFirstName;

    @Schema(description = "Фамилия автора объявления", example = "Иванов")
    private String authorLastName;

    @Schema(description = "Описание объявления", example = "Как новый, без царапин")
    private String description;

    @Schema(description = "Email автора объявления", example = "user@gmail.com")
    private String email;

    @Schema(description = "Ссылка на картинку объявления", example = "/image/ad/1")
    private String image;

    @Schema(description = "Телефон автора объявления", example = "+79991234567")
    private String phone;

    @Schema(description = "Цена объявления", example = "10000")
    private Integer price;

    @Schema(description = "Заголовок объявления", example = "Велосипед")
    private String title;

    @Schema(description = "Ссылка на аватар автора", example = "/image/user/1")
    private String authorImage;
}