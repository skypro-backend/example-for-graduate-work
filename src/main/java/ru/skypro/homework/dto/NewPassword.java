package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class NewPassword {
    public String currentPassword;
    public String newPassword;
}