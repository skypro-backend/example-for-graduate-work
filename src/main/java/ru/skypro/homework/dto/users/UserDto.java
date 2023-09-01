package ru.skypro.homework.dto.users;

import lombok.Data;
import ru.skypro.homework.entity.users.Role;

import javax.validation.constraints.Email;

/**
 * DTO for {@link ru.skypro.homework.entity.users.User}
 */
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
