package ru.skypro.homework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.skypro.homework.dto.Role;
import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String userName;
    private String password;
    private Role role;
    @OneToOne
    private Avatar avatar;
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
    @OneToMany(mappedBy = "user")
    private List<Ad> ads;
    public User(int id, String firstName, String lastName, String phone, String email, String userName, String password, Role role, Avatar avatar) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.avatar = avatar;
    }
}
