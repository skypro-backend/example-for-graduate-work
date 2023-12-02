package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private int id = 0;
    private String email = "string";
    private String firstName = "string";
    private String lastName = "string";
    private String phone = "string";
    @Enumerated(EnumType.STRING)
    private Role role = Role.valueOf("USER");
    private String image = "string";

}
