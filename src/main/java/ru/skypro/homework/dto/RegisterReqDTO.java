package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class RegisterReqDTO {
    // Логин
    private String username;
    // Пароль
    private String password;
    // Имя пользователя
    private String firstName;
    // Фамилия пользователя
    private String lastName;
    // Телефон пользователя
    private String phone;
    // Роль пользователя
    private Role role;

}
