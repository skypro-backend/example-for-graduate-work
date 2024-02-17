package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Password {
    private String currentPassword;

    private String newPassword;
}