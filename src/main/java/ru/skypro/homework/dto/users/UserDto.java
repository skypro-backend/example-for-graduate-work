package ru.skypro.homework.dto.users;

import lombok.Data;
import ru.skypro.homework.entity.roles.Role;

import javax.validation.constraints.Email;
@Data
public class UserDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    @Email
    private String email;
    private String image;
    private Role role;
}
