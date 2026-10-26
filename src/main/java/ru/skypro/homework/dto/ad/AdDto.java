package ru.skypro.homework.dto.ad;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Информация об объявлении")
public record AdDto(
        @Schema(description = "id автора объявления") Integer author,
        @Schema(description = "ссылка на картинку объявления") String image,
        @Schema(description = "id объявления") Integer pk,
        @Schema(description = "цена объявления") Integer price,
        @Schema(description = "заголовок объявления") String title
) {
}
