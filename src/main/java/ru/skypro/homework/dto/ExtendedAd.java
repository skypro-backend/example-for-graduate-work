package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Расширенная модель данных объявления, запрашиваемая клиентом.
 * Соединяет параметры публикации с развернутыми контактными данными продавца для карточки товара.
 */
@Data
@Schema(description = "Расширенная информация об объявлении")
public class ExtendedAd {
    @Schema(description = "id объявления")
    private Integer pk;

    @Schema(description = "Имя автора объявления")
    private String authorFirstName;

    @Schema(description = "Фамилия автора объявления")
    private String authorLastName;

    @Schema(description = "Описание объявления")
    private String description;

    @Schema(description = "Логин автора объявления")
    private String email;

    @Schema(description = "Ссылка на картинку объявления")
    private String image;

    @Schema(description = "Телефон автора объявления")
    private String phone;

    @Schema(description = "Цена объявления")
    private Integer price;

    @Schema(description = "Заголовок объявления")
    private String title;
}