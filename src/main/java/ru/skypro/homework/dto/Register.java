package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Регистрация пользователя")
public class Register {


    @Schema(description = "логин")
    @Size(min = 4,max = 32)
    @NotBlank(message ="Введите логин")
    private String username;

    @Schema(description = "пароль")
    @Size(min = 8,max = 16)
    @NotBlank(message ="Введите пароль")
    private String password;

    @Schema(description = "имя пользователя")
    @Size(min = 2,max = 16)
    @NotBlank(message ="Заполните имя пользователя")
    private String firstName;


    @Schema(description = "фамилия пользователя")
    @Size(min = 2,max = 16)
    @NotBlank(message ="Заполните фамилию пользователя")
    private String lastName;


    @Schema(description = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @NotBlank(message ="Заполните номер телефона")
    private String phone;

    @Schema(description = "роль пользователя")
    private Role role;
}
