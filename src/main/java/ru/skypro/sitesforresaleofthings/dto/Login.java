package ru.skypro.sitesforresaleofthings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO авторизации
 */

/**
 * Свойства:
 * 1) username - логин,
 * 2) password - пароль
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login {

    private String username;
    private String password;
}