package ru.skypro.homework.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Форма частичного обновления анкетных и контактных данных пользователя.
 * Позволяет изменить имя, фамилию или телефон без изменения параметров безопасности.
 */
@Data
@Schema(description = "Данные для обновления профиля")
public class UpdateUser {
    @Schema(description = "Имя пользователя", example = "Иван")
    @Size(min = 3, max = 10)
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Иванов")
    @Size(min = 3, max = 10)
    private String lastName;

    @Schema(description = "Телефон пользователя", example = "+7(999)999-99-99")
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    private String phone;
}