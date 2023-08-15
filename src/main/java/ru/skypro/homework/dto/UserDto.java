package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    //    id пользователя
    private Integer id;
    //    логин пользователя
    private String email;
    //    имя пользователя
    private String firstName;
    //    фамилия пользователя
    private String lastName;
    //    телефон пользователя
    private String phone;
    //    роль пользователя
    private String role;
    //    ссылка на аватар пользователя
    private String image;
}
