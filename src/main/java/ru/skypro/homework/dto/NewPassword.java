package ru.skypro.homework.dto;

import lombok.Data;

/**
 * сущность NewPassword
 */
@Data
public class NewPassword {
    /**
     * текущий пароль
     */
    String currentPassword;
    /**
     * новый пароль
     */
    String newPassword;
}
