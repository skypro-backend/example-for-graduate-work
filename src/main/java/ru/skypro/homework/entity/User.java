package ru.skypro.homework.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false, unique = true)
    private String email;


    @Column(nullable = false)
    private String password;


    private String firstName;


    private String lastName;


    private String phone;


    @Enumerated(EnumType.STRING)
    private Role role;


    private String image;


    @OneToMany(mappedBy = "author")
    private List<Ad> ads;


    @OneToMany(mappedBy = "author")
    private List<Comment> comments;
}