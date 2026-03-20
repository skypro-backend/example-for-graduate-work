package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Данные для регистрации")
public class Register {

    @NotBlank
    @Size(min = 4, max = 32)
    @Schema(description = "Логин", example = "user@mail.com")
    private String username;

    @NotBlank
    @Size(min = 8, max = 16)
    @Schema(description = "Пароль", example = "password123")
    private String password;

    @NotBlank
    @Size(min = 2, max = 16)
    @Schema(description = "Имя пользователя", example = "Ivan")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 16)
    @Schema(description = "Фамилия пользователя", example = "Ivanov")
    private String lastName;

    @NotBlank
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Schema(description = "Телефон пользователя", example = "+7 999 123-45-67")
    private String phone;

    @NotNull
    @Schema(description = "Роль пользователя")
    private Role role;
}