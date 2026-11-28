package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Данные для обновления пользователя")
public class UpdateUser {
    @Schema(description = "имя пользователя", minLength = 3, maxLength = 10, example = "Иван")
    private String firstName;

    @Schema(description = "фамилия пользователя", minLength = 3, maxLength = 10, example = "Иванов")
    private String lastName;

    @Schema(description = "телефон пользователя", example = "+7 (999) 123-45-67")
    private String phone;
}