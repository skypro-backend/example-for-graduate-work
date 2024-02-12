package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * сущность Register
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
    @Size(min =8, max = 16)
    @Schema(description = "пароль")
    private String password;

    /**
     * имя пользователя
     */
    @Size(min =2, max = 16)
    @Schema(description = "имя пользователя")
    private String firstName;

    /**
     * фамилия пользователя
     */
    @Size(min =2, max = 16)
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
