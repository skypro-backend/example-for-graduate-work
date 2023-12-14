package ru.skypro.homework.dto.users;

import lombok.Data;

@Data
public class NewPassword {

    private String currentPassword;
    private String newPassword;
}
