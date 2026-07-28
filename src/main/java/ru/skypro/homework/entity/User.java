package ru.skypro.homework.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.skypro.homework.constants.ApiConstants;
import ru.skypro.homework.entity.Role;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", nullable = false, unique = true, length = ApiConstants.USERNAME_LENGTH)
    private String email;

    @Column(name = "password", nullable = false, length = ApiConstants.PASSWORD_LENGTH)
    private String password;

    @Column(name = "first_name", nullable = false, length = ApiConstants.NAME_LENGTH)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = ApiConstants.NAME_LENGTH)
    private String lastName;

    @Column(name = "phone", length = ApiConstants.PHONE_LENGTH)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = ApiConstants.ROLE_LENGTH)
    private Role role;

    @Column(name = "image", length = ApiConstants.IMAGE_PATH_LENGTH)
    private String image;
}