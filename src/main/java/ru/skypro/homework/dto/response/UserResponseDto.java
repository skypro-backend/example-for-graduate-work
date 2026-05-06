package ru.skypro.homework.dto.response;

import lombok.Value;
import ru.skypro.homework.entity.Role;

@Value
public class UserResponseDto {
    Integer id;
    String email;
    String firstName;
    String lastName;
    String phone;
    Role role;
    String image;
}
