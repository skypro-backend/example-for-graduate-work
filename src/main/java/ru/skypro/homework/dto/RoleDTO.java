package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "роль пользователя")
public enum RoleDTO {
    USER, ADMIN
}
