package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String role;
    private String image;
}
