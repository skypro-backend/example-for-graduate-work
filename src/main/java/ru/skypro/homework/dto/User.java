package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Пользователь")
public class User {

    @Schema(description = "id пользователя", example = "1")
    private int id;

    @Schema(description = "логин пользователя (email)", example = "user@gmail.com")
    private String email;

    @Schema(description = "имя пользователя", example = "Антон")
    private String firstName;

    @Schema(description = "фамилия пользователя", example = "Сопетов")
    private String lastName;

    @Schema(description = "телефон пользователя", example = "+79991112233")
    private String phone;

    @Schema(description = "роль пользователя", example = "USER")
    private Role role;

    @Schema(description = "ссылка на аватарку пользователя", example = "/images/avatars/1")
    private String image;
}