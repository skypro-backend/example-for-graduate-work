package ru.skypro.sitesforresaleofthings.dto;

import lombok.Data;
import ru.skypro.sitesforresaleofthings.constant.Regex;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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

    @NotBlank
    @Size(min = 3, max = 10)
    private String firstName;
    @NotBlank
    @Size(min = 3, max = 10)
    private String lastName;
    @Pattern(regexp = Regex.PHONE_REGEX)
    private String phone;
}