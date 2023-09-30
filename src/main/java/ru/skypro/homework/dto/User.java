package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class User {
    String phone;
    String lastName;
    String firstName;
    String email;
    Integer id;
}
