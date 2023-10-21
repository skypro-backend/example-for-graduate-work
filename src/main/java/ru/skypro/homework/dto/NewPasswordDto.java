package ru.skypro.homework.dto;

public record NewPasswordDto(
        /**
         minLength: 8
         maxLength: 16
         текущий пароль
         */
        String currentPassword,
        /**
         minLength: 8
         maxLength: 16
         новый пароль
         */
        String newPassword
) {
}
