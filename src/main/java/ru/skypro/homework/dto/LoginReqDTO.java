package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class LoginReqDTO {
    // Пароль
    private String password;
    // Логин
    private String username;

}
