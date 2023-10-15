package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor

@Data
public class NewPassword {
    private String currentPassword;
    private String newPassword;
}
