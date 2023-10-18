package ru.skypro.homework.dto;

public record UpdatePasswordDto(
        String currentPassword,
        String newPassword
) {
}
