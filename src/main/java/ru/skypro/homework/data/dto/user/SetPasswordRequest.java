package ru.skypro.homework.data.dto.user;

import lombok.Data;

@Data
public class SetPasswordRequest {
    private String currentPassword;
    private String newPassword;
}
