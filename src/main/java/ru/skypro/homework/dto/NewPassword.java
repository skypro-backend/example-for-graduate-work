package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class NewPassword {
    private String currentPassword;
    private String confirmPhoneNumber;
    private String newPassword;

}
