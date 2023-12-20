package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String gender;
    private String phoneNumber;
    private Role userRole;
    private String idImage;
    private int userBirthday;

    public Integer getId() {
        return id;
    }
}

