package ru.skypro.homework.dto;

import ru.skypro.homework.enums.Role;

public record AuthorizedUserInfoDto(
        Long id,
        String email,
        String firstName,
        String lastName,
        String phone,
        Role role,
        String image
) {
}
