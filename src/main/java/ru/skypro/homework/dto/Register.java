package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Данные для регистрации пользователя")
public class Register {

    @Schema(description = "Логин пользователя")
    @Email
    @NotBlank
    private String username;

    @Schema(description = "Пароль пользователя")
    @NotBlank
    @Size(min = 8, max = 16)
    private String password;

    @Schema(description = "Имя пользователя")
    @NotBlank
    private String firstName;

    @Schema(description = "Фамилия пользователя")
    @NotBlank
    private String lastName;

    @Schema(description = "Телефон пользователя")
    @NotBlank
    private String phone;

    @Schema(description = "Роль пользователя")
    private Role role;
}
