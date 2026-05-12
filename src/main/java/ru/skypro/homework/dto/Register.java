package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.skypro.homework.entity.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Данные для регистрации пользователя")
public class Register {
    @Schema(description = "Email (логин)", example = "user@gmail.com", format = "email")
    @Email(message = "Некорректный email")
    @NotBlank(message = "Email обязателен")
    private String username;

    @Schema(description = "Пароль", example = "password123", minLength = 8, maxLength = 16)
    @NotBlank(message = "Пароль обязателен")
    @Size(min = 8, max = 16, message = "Пароль должен быть от 8 до 16 символов")
    private String password;

    @Schema(description = "Имя пользователя", example = "Иван", minLength = 2, maxLength = 16)
    @NotBlank(message = "Имя обязательно")
    @Size(min = 2, max = 16, message = "Имя должно быть от 2 до 16 символов")
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Иванов", minLength = 2, maxLength = 16)
    @NotBlank(message = "Фамилия обязательна")
    @Size(min = 2, max = 16, message = "Фамилия должна быть от 2 до 16 символов")
    private String lastName;

    @Schema(description = "Телефон пользователя", example = "+7 (999) 123-45-67", pattern = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @NotBlank(message = "Телефон обязателен")
    @Pattern(regexp = "^\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}$", message = "Телефон должен соответствовать формату +7 (XXX) XXX-XX-XX")
    private String phone;

    @Schema(description = "Роль пользователя", allowableValues = {"USER", "ADMIN"})
    private Role role;
}