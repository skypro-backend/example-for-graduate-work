package ru.skypro.homework.projections;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class NewPassword {
    private   String currentPassword; // min 8; max 16
    private   String newPassword;
}