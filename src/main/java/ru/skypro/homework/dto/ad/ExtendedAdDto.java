package ru.skypro.homework.dto.ad;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "расширенное представление объявления")
public record ExtendedAdDto(
        @Schema(description = "id объявления") Integer pk,
        @Schema(description = "Имя автора объявления") String authorFirstName,
        @Schema(description = "фамилия автора объявления") String authorLastName,
        @Schema(description = "описание объявления") String description,
        @Schema(description = "логин автора объявления") String email,
        @Schema(description = "ссылка на картинку объявления") String image,
        @Schema(description = "телефон автора объявления") String phone,
        @Schema(description = "цена объявления") Integer price,
        @Schema(description = "заголовок объявления") String title
) {
}
