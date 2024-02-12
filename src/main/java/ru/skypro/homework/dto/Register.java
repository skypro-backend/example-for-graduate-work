package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Register - регистрация пользователя
 * <br><i>содержит следующие поля:</i>
 * <br>- username <i>(логин)</i>;
 * <br>- password <i>(пароль)</i>;
 * <br>- firstName <i>(имя пользователя)</i>;
 * <br>- lastName <i>(фамилия пользователя)</i>;
 * <br>- phone <i>(телефон пользователя)</i>;
 * <br>- role <i>(роль пользователя, {@link Role})</i>;
 */

@Data
public class Register {
    /**
     * логин
     */
    @Email
    @Size(min = 4, max = 32)
    @Schema(description = "логин")
    private String username;

    /**
     * пароль
     */
    @Size(min = 8, max = 16)
    @Schema(description = "пароль")
    private String password;

    /**
     * имя пользователя
     */
    @Size(min = 2, max = 16)
    @Schema(description = "имя пользователя")
    private String firstName;

    /**
     * фамилия пользователя
     */
    @Size(min = 2, max = 16)
    @Schema(description = "фамилия пользователя")
    private String lastName;

    /**
     * телефон пользователя
     */
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Schema(description = "телефон пользователя", example = "+7(987)654-32-10")
    private String phone;

    /**
     * роль пользователя
     */
    @Schema(description = "роль пользователя")
    private Role role;
}
