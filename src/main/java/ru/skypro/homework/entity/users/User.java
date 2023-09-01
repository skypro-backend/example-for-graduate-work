package ru.skypro.homework.entity.users;

import lombok.Getter;
import lombok.Setter;
import ru.skypro.homework.entity.ads.Ad;
import ru.skypro.homework.entity.comments.Comment;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "image")
    private String image;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private Integer password;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Ad> ads;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Comment> comments;

}