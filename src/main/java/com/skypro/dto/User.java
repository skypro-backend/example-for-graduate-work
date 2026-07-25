package com.skypro.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для представления информации о пользователе.
 * <p>
 * Используется в ответах на запросы {@code GET /users/me} и {@code GET /users/{id}}.
 * Содержит полную информацию о пользователе, включая роль и ссылку на аватар.
 * </p>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Schema(description = "id пользователя")
    private Integer id;

    @Schema(description = "логин пользователя")
    private String username;

    @Schema(description = "имя пользователя")
    private String firstName;

    @Schema(description = "фамилия пользователя")
    private String lastName;

    @Schema(description = "телефон пользователя")
    private String phone;

    @Schema(description = "роль пользователя")
    private String role;

    @Schema(description = "ссылка на аватар пользователя")
    private String image;
}
