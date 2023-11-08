package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "роли пользователя")
public enum Role {
    USER, ADMIN
}
