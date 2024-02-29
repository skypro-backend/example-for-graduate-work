package ru.skypro.sitesforresaleofthings.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * DTO пароля
 */

/**
 * Свойства:
 * 1) currentPassword - текущий пароль,
 * 2) newPassword - новый пароль
 */
@Data
public class NewPasswordDTO {

    @NotBlank
    @Size(min = 8, max = 16)
    private String currentPassword;
    @NotBlank
    @Size(min = 8, max = 16)
    private String newPassword;
}