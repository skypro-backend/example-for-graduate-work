package ru.skypro.homework.dto.users;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Schema(description = "Данные для обновления информации о пользователе")
public record UpdateUserDto(
        @Schema(description = "имя пользователя", minLength = 3, maxLength = 10)
        @Size(min = 3, max = 10)
        String firstName,

        @Schema(description = "фамилия пользователя", minLength = 3, maxLength = 10)
        @Size(min = 3, max = 10)
        String lastName,

        @Schema(description = "телефон пользователя")
        @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
        String phone
) {

}
