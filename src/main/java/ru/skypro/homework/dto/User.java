package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Информация о пользователе")
public class User {

    @Schema(description = "id пользователя", example = "1")
    private Integer id;

    @Schema(description = "логин пользователя", example = "user@mail.ru")
    private String email;

    @Schema(description = "имя пользователя", example = "Иван")
    private String firstName;

    @Schema(description = "фамилия пользователя", example = "Иванов")
    private String lastName;

    @Schema(description = "телефон пользователя", example = "+7-999-999-99-99")
    private String phone;

    @Schema(description = "роль пользователя", example = "USER")
    private String role;

    @Schema(description = "ссылка на аватар", example = "/images/1.jpg")
    private String image;
}