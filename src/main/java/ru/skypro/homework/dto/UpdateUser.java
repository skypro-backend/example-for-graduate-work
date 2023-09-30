package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UpdateUser {

    String phone;
    String firstName;
    String lastName;
    String email;
    String password;

}
