package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Перечисление возможных ролей пользователей в системе для контроля доступа.
 */
@Schema(description = "Роль пользователя")
public enum Role {
    USER, ADMIN
}