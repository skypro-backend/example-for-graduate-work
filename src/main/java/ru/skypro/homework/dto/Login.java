package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Login {
    /**
     * Имя пользователя
     */
    private String username;
    /**
     * Пароль пользователя
     */
    private String password;
}
