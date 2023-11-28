package ru.skypro.homework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.RoleDto;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private RoleDto role;
}
