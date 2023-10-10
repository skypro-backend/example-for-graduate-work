package ru.skypro.homework.dto;

import lombok.Data;

/**
 * Класс DTO для передачи данных при авторизации пользователя
 */
@Data
public class Login {

    private String username; //логин
    private String password; //пароль
}
