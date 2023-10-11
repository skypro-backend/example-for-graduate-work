package ru.skypro.homework.pojo;

import lombok.Data;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userID;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne
    @JoinColumn (name = "avatar_id", insertable=false, updatable=false)
    private Avatar avatar;

    @Column (name = "avatar_id")
    private Long avatarId;

    @Column (name = "password")
    private String password;

    public User() {
    }



}
