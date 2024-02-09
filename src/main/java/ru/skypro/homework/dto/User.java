package ru.skypro.homework.dto;

import lombok.Data;

/**
 * Сущность User
 */
@Data
public class User {
    /**
     * id пользователя
     */
    Integer id;
    /**
     * логин пользователя
     */
    String email;
    /**
     * имя пользователя
     */
    String firstName;
    /**
     * фамилия пользователя
     */
    String lastName;
    /**
     * телефон пользователя
     */
    String phone;
    /**
     * роль пользователя
     */
    Role role;
    /**
     * ссылка на аватар пользователя
     */
    String image;
}

