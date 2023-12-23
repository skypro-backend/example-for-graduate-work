package ru.skypro.homework.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "login")
public class Login {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;
}
