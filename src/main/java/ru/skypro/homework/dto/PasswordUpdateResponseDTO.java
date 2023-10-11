package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class PasswordUpdateResponseDTO {

    private String currentPassword;
    private String newPassword;

    public PasswordUpdateResponseDTO(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}
