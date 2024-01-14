package ru.skypro.kakavito.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.kakavito.dto.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The User class represents a user entity in the database. It stores information about a user,
 * including their unique identifier (id), username, password, first name, last name, phone number, email associated image and role.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    /**
     * Id пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Email - он же login
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * Пароль пользователя
     */
    @Column(nullable = false)
    private String password;

    /**
     * Имя пользователя
     */
    @Column(nullable = false)
    private String firstName;

    /**
     * Фамилия пользователя
     */
    @Column(nullable = false)
    private String lastName;

    /**
     * Номер телефона
     */
    @Column(nullable = false)
    private String phone;

    /**
     * Роль
     */
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Id картинки
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image image;

    /**
     * Коллекция объявлений
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ad> ads = new HashSet<>();

    /**
     * Коллекция комментариев
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<>();
}
