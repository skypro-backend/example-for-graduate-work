package ru.skypro.homework.dto.Users;

import lombok.Data;

@Data
public class NewPasswordDto {

    private String currentPassword;
    private String newPassword;
}
