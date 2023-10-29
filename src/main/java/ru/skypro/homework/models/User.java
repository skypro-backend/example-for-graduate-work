package ru.skypro.homework.models;

import lombok.*;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"comments", "ads"})
@ToString(exclude = {"comments", "ads"})
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @Column(name = "user_name")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "avatar_path")
    private String avatarPath;
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
    @OneToMany(mappedBy = "user")
    private List<Ad> ads;
}
