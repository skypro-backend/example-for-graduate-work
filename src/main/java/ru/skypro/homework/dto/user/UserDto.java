package ru.skypro.homework.dto.user;

import lombok.Data;
import ru.skypro.homework.entity.roles.Role;

@Data
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;

    private String email;
    private String image;
    private Role role;
}
