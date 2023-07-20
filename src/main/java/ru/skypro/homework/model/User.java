package ru.skypro.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.RoleDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pk_id")
    private List<Ad> ad;

    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleDto roleDto;

    private String image;

}
