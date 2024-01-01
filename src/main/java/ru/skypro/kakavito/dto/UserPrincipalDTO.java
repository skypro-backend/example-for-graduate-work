package ru.skypro.kakavito.dto;

import lombok.Data;

@Data
public class UserPrincipalDTO {

    private Integer id;
    private String email;
    private String password;
    private Role role;
}
