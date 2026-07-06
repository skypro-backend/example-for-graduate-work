package ru.skypro.homework.user;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Имя пользователя")
public class NewPassword {

    @Schema(description = "Текущий пароль пользователя", example = "1q2w3e")
    private String currentPassword;

    @Schema(description = "Новый пароль пользователя", example = "11qq22")
    private String newPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
