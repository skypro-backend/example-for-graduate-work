package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.Role;
import javax.persistence.*;

/**
 * JPA-сущность для представления пользователя в базе данных (таблица "users").
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 32)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 16)
    private String firstName;

    @Column(length = 16)
    private String lastName;

    @Column(length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private String image;
}