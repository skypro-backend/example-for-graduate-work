package ru.skypro.homework.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO для информации о пользователе.
 * Используется для передачи данных пользователя в ответах на запросы.
 * Основные функции:
 * - предоставление полных данных о пользователе (ID, логин, имя, фамилия, телефон, роль, аватар);
 * - используется в ответах на GET /users/me и других запросах.
 */
@Data
@Schema(description = "Информация о пользователе")
public class User {

    @Schema(description = "ID пользователя")
    private Integer id;

    @Schema(description = "Логин пользователя")
    private String email;

    @Schema(description = "Имя пользователя")
    private String firstName;

    @Schema(description = "Фамилия пользователя")
    private String lastName;

    @Schema(description = "Телефон пользователя")
    private String phone;

    @Schema(description = "Роль пользователя", allowableValues = {"USER", "ADMIN"})
    private String role;

    @Schema(description = "Ссылка на аватар пользователя")
    private String image;
}
