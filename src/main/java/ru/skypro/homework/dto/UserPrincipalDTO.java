package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class UserPrincipalDTO {

    private Integer id;
    private String email;
    private String password;
    private Role role;
}
