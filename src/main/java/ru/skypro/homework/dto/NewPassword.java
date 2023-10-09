package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO нового пароля.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPassword {

    private String currentPassword; // текущий пароль
    private String newPassword; // новый пароль
}