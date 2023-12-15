package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String gender;
    private String phoneNumber;
    private Role userRole;
    private String idImage;
    private int userBirthday;
}

