package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class NewPasswordDto {
    String currentPassword;
    String newPassword;
}
