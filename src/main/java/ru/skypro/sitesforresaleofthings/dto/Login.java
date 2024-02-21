package ru.skypro.sitesforresaleofthings.dto;

import lombok.Data;

/**
 * DTO авторизации
 */

/**
 * Свойства:
 * 1) username - логин,
 * 2) password - пароль
 */
@Data
public class Login {

    private String username;
    private String password;
}