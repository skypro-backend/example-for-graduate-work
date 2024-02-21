package ru.skypro.sitesforresaleofthings.dto;

import lombok.Data;

/**
 * DTO обновления пользователя
 */

/**
 * Свойства:
 * 1) firstName - имя пользователя,
 * 2) lastName - фамилия пользователя,
 * 3) phone - телефон пользователя
 */
@Data
public class UpdateUserDTO {

    private String firstName;
    private String lastName;
    private String phone;
}