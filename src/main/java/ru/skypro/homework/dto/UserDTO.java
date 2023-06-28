package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String image;
}
