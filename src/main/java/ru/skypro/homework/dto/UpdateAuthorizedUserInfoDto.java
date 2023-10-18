package ru.skypro.homework.dto;

public record UpdateAuthorizedUserInfoDto(
        String firstName,
        String lastName,
        String phone
) {
}
