package ru.skypro.homework.dto.users;

import lombok.Data;

@Data
public class NewPasswordDto {

    private String currentPassword;
    private String newPassword;
}
