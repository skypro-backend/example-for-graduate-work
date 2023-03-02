package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class User {
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Integer id;
}
