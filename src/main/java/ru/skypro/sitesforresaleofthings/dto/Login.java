package ru.skypro.sitesforresaleofthings.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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

    @NotBlank
    @Size(min = 4, max = 32)
    private String username;
    @NotBlank
    @Size(min = 8, max = 16)
    private String password;
}