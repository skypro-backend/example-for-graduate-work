package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Role;

/**
 * RegisterDto is the Data Transfer Object used for user registration
 * @author radyushinaalena and AlexBoko
 */
@Data
public class RegisterDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
}
