package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UpdateUserDTO {
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
}
