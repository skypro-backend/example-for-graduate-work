package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Обновление пользователя")
public class UpdateUser {

    @Schema(description = "Имя пользователя", example = "Антон")
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Сопетов")
    private String lastName;

    @Schema(description = "Телефон пользователя", example = "+79991112233")
    private String phone;
}