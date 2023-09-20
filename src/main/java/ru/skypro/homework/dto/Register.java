package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
@Data
public class Register {

    private String username; //minLength: 4, maxLength: 32
    private String password; //minLength: 8, maxLength: 16
    private String firstName; //minLength: 2, maxLength: 16
    private String lastName; // //minLength: 8, maxLength: 16
    private String phone;//pattern: \+7\s?\(?\d{3}\)?\s?\d{3}-?\d{2}-?\d{2} телефон пользователя
    private Role role; //Enum array[USER, ADMIN]

}
