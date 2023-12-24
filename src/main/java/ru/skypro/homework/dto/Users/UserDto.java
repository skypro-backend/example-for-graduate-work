package ru.skypro.homework.dto.Users;

import lombok.Data;
import ru.skypro.homework.dto.Role;

@Data
public class UserDto {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String role;
    private String image;

}
