package ru.skypro.kakavito.dto;


import lombok.Data;

/**
 * Создание ДТО
 */
@Data
public class UserDTO {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private String image;

}
