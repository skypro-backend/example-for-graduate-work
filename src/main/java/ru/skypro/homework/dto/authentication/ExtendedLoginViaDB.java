package ru.skypro.homework.dto.authentication;

import lombok.Data;
import ru.skypro.homework.dto.Role;


@Data
public class ExtendedLoginViaDB {

    private Role role;
    private String username;
    private String password;
}
