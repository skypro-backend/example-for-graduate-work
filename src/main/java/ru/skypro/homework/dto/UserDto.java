package ru.skypro.homework.dto;

import lombok.Data;
import ru.skypro.homework.model.Role;

/**
 * UserDto is the Data Transfer Object used to get information about an authorized user
 * @author radyushinaalena and AlexBoko
 */
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

