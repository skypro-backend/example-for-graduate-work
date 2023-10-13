package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class NewPassDto {

    private String currentPassword;

    private String newPassword;
}
