package ru.skypro.homework.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false)
    private int id;
    @Column(name = "login",nullable = false)
    private String login;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "image")
    private String image;
    @Column(name = "role",nullable = false)
    private Role role;

    @OneToMany(mappedBy="userModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AdModel> adModels;

    @OneToMany(mappedBy = "userModel",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<CommentModel> commentModels;

}