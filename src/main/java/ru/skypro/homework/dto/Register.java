package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class Register {

    private String username; // логин
    private String password; // пароль
    private String firstName; // имя пользователя
    private String lastName; // фамилия пользователя
    private String phone; // телефон пользователя
    private Role role; // роль пользователя
}
