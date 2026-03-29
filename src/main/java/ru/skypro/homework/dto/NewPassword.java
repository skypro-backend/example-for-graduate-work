package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Size;

@Schema(description = "Данные для смены пароля")
public class NewPassword {

    @Schema(description = "текущий пароль",
            minLength = 8,
            maxLength = 16,
            example = "currentPass123")
    @Size(min = 8, max = 16)
    private String currentPassword;

    @Schema(description = "новый пароль",
            minLength = 8,
            maxLength = 16,
            example = "newPass123")
    @Size(min = 8, max = 16)
    private String newPassword;

    public NewPassword() {}

    public NewPassword(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
