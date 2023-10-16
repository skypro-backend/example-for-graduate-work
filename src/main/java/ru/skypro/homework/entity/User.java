package ru.skypro.homework.entity;

import lombok.Data;
import ru.skypro.homework.dto.authdto.Role;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false)
    private int id;

    @Column(name = "email",length = 50, nullable = false)
    private String email;

    @Column(name = "firstName",length = 255,  nullable = false)
    private String firstName;

    @Column(name = "lastName",length = 255,  nullable = false)
    private String lastName;

    @Column(name = "phone",length = 20 ,  nullable = false)
    private String phone;

    @Column(name = "role", nullable = false)
    private Role role;

    @Column(name = "image_id")
    @ManyToOne
    private Image image;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ad> ad;
}
