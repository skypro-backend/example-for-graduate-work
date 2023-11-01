package ru.skypro.homework.entity;

import lombok.Data;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import javax.validation.constraints.Pattern;


@Entity
@Data
@Table(name = "users")
public class  User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String phone;
    private String firstName;
    private String lastName;
    private String email;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @Enumerated(EnumType.STRING)
    private Role role;

}
