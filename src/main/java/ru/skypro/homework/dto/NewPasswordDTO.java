package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor

@Data
public class NewPasswordDTO {
    private   String currentPassword;
    private   String newPassword;
}
