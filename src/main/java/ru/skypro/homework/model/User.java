package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String image;

    @Enumerated(EnumType.STRING)
    private Role role;

}