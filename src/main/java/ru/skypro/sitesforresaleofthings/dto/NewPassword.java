package ru.skypro.sitesforresaleofthings.dto;

import lombok.Data;

/**
 * DTO пароля
 */

/**
 * Свойства:
 * 1) currentPassword - текущий пароль,
 * 2) newPassword - новый пароль
 */
@Data
public class NewPassword {

    private String currentPassword;
    private String newPassword;
}