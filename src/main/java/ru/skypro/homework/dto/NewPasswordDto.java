package ru.skypro.homework.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NewPasswordDto {
    private String currentPassword;
    private String newPassword;
    private String oldPassword;
}
