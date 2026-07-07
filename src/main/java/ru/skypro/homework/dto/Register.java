package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Register {

    @NotNull
    @Size(min = 4, max = 32)
    @Schema(description = "Уникальное имя пользователя (логин)", example = "katya_sky")
    private String username;

    @NotNull
    @Size(min = 8, max = 16)
    @Schema(description = "Пароль для регистрации", example = "mySecretPass")
    private String password;

    @NotNull
    @Size(min = 2, max = 16)
    @Schema(description = "Имя пользователя", example = "Катя")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 16)
    @Schema(description = "Фамилия пользователя", example = "Иванова")
    private String lastName;

    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}", message = "Неверный формат телефона")
    @Schema(description = "Контактный телефон в формате +7...", example = "+79991234567")
    private String phone;

    @Schema(description = "Роль пользователя при регистрации", example = "USER")
    private Role role;
}
