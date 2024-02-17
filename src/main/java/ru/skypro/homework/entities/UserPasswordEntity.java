package ru.skypro.homework.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_password")
public class UserPasswordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
}