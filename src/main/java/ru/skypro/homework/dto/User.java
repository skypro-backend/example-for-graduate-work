package ru.skypro.homework.dto;

import lombok.*;

import javax.validation.constraints.Email;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    @Email
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    private String image;

}
