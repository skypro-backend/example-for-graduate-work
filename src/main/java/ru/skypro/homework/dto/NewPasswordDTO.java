package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class NewPasswordDTO {
    /**
     * текущий пароль
     */
    String currentPassword;
    /**
     * новый пароль
     */
    String newPassword;
}
