package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Role;


@Data
public class UserDto {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private String image;
}
