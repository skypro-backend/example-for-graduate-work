package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDTO {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String role;
    private String image;
}
