package ru.skypro.homework.models.dto;

import lombok.Data;

@Data
public class UserDto {

    private String email;
    private String firstName;
    private Integer id;
    private String lastName;
    private String phone;

}
