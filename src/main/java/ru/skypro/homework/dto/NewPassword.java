package ru.skypro.homework.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewPassword {
    @NotBlank(message = "Текущий пароль не должен быть пустым")
    private String currentPassword;

    @NotBlank(message = "Новый пароль не должен быть пустым")
    @Size(min = 6, message = "Новый пароль должен быть не короче 6 символов")
    private String newPassword;

    public String getCurrentPassword() { return currentPassword; }
    public void setCurrentPassword(String currentPassword) { this.currentPassword = currentPassword; }

    public String getNewPassword() { return newPassword; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
}
