package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class NewPassword {
    private String currentPassword;
    private String newPassword;
}
