package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class NewPasswordDTO {
    // Текущий пароль
    private String currentPassword;
    // Новый пароль
    private String newPassword;
}
