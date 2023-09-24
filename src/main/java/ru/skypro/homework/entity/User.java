package ru.skypro.homework.entity;

import lombok.Data;

import javax.persistence.*;
@Entity
@Data
@Table(name = "author")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String image;

}
