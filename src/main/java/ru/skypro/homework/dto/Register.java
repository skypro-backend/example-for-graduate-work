package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "Данные для регистрации пользователя")
@Data
public class Register {

    @Schema(description = "Логин пользователя")
    private String username;

    @Schema(description = "Пароль пользователя")
    private String password;

    @Schema(description = "Имя пользователя")
    private String firstName;

    @Schema(description = "Фамилия пользователя")
    private String lastName;

    @Schema(description = "Телефон пользователя")
    private String phone;

    @Schema(description = "Роль пользователя")
    private Role role;
}
