package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class NewPasswordDTO {
    public String currentPassword;
    public String newPassword;

    public NewPasswordDTO(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}
