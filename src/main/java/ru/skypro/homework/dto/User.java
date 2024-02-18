package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * User - профиль пользователя
 * <br><i>содержит следующие поля:</i>
 * <br>- id <i>(id пользователя)</i>;
 * <br>- email <i>(логин пользователя)</i>;
 * <br>- firstName <i>(имя пользователя)</i>;
 * <br>- lastName <i>(фамилия пользователя)</i>;
 * <br>- phone <i>(телефон пользователя)</i>;
 * <br>- role <i>(роль пользователя, {@link Role})</i>;
 * <br>- image <i>(ссылка на аватар пользователя)</i>.
 */
@Data
public class User {
    /**
     * id пользователя
     */
    @Schema(description = "id пользователя")
    int id;

    /**
     * логин пользователя
     */
    @Schema(description = "логин пользователя")
    String email;

    /**
     * имя пользователя
     */
    @Schema(description = "имя пользователя")
    String firstName;

    /**
     * фамилия пользователя
     */
    @Schema(description = "фамилия пользователя")
    String lastName;

    /**
     * телефон пользователя
     */
    @Schema(description = "телефон пользователя")
    String phone;

    /**
     * роль пользователя
     */
    @Schema(description = "роль пользователя")
    Role role;

    /**
     * ссылка на аватар пользователя
     */
    @Schema(description = "ссылка на аватар пользователя")
    String image;
}

