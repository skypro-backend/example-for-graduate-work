package com.skypro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для детального представления объявления.
 * <p>
 * Используется в ответе на запрос {@code GET /ads/{id}}.
 * Содержит полную информацию об объявлении и авторе, включая имя, фамилию, телефон и email.
 * </p>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExtendedAd {

    @Schema(description = "id объявления")
    private Integer pk;

    @Schema(description = "имя автора объявления")
    private String authorFirstName;

    @Schema(description = "фамилия автора объявления")
    private String authorLastName;

    @Schema(description = "описание объявления")
    private String description;

    @Schema(description = "логин автора объявления")
    private String email;

    @Schema(description = "ссылка на картинку объявления")
    private String image;

    @Schema(description = "телефон автора объявления")
    private String phone;

    @Schema(description = "цена объявления")
    private Integer price;

    @Schema(description = "заголовок объявления")
    private String title;
}
