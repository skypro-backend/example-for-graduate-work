package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class SetPasswordDto {
    public String currentPassword;
    public String newPassword;
}