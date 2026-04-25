package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Информация о пользователе")
public class User {
    @Schema(description = "ID пользователя", example = "1")
    private Integer id;

    @Schema(description = "Email пользователя", example = "user@gmail.com")
    private String email;

    @Schema(description = "Имя пользователя", example = "Иван")
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Иванов")
    private String lastName;

    @Schema(description = "Телефон пользователя", example = "+79991234567")
    private String phone;

    @Schema(description = "Роль пользователя", allowableValues = {"USER", "ADMIN"})
    private Role role;

    @Schema(description = "Ссылка на аватар пользователя", example = "/images/user/1.jpg")
    private String image;
}