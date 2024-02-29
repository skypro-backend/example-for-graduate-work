package ru.skypro.sitesforresaleofthings.dto;

import lombok.Data;
import ru.skypro.sitesforresaleofthings.constant.Regex;
import ru.skypro.sitesforresaleofthings.constant.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * DTO регистрации
 */

/**
 * Свойства:
 * 1) username - логин пользователя,
 * 2) password - пароль пользователя,
 * 3) firstName - имя пользователя,
 * 4) lastName - фамилия пользователя,
 * 5) phone - телефон пользователя,
 * 6) role - роль пользователя (USER, ADMIN)
 */
@Data
public class Register {

    @NotBlank
    @Size(min = 4, max = 32)
    private String username;
    @NotBlank
    @Size(min = 8, max = 16)
    private String password;
    @NotBlank
    @Size(min = 2, max = 16)
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 16)
    private String lastName;
    @Pattern(regexp = Regex.PHONE_REGEX)
    private String phone;
    private Role role;
}