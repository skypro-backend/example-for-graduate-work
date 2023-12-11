package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class NewPasswordDTO {
    String currentPassword;
    String newPassword;
}
