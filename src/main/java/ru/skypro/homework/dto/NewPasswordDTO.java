package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class NewPasswordDTO {
    private   String currentPassword; // min 8; max 16
    private   String newPassword;
}
