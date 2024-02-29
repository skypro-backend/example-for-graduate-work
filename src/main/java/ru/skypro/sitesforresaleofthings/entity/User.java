package ru.skypro.sitesforresaleofthings.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.sitesforresaleofthings.constant.Role;

import javax.persistence.*;
import java.util.Collection;

/**
 * Создаем сущность "Пользователь"
 */
@Entity
/**
 * Создаем таблицу users(Пользователи), имеющую следующие свойства-колонки:
 * 1) id - id пользователя,
 * 2) username - логин пользователя,
 * 3) firstName - имя пользователя,
 * 4) lastName - фамилия пользователя,
 * 5) email - электронная почта пользователя,
 * 6) password - пароль пользователя,
 * 7) phone - телефон пользователя,
 * 8) role - роль пользователя (USER, ADMIN),
 * 9) image - ссылка на аватар пользователя,
 * 10) ads - объявления пользователя
 */
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "user_role")
    private Role role;

    @Column(name = "image_user")
    private String image;

    @OneToMany(mappedBy = "author")
    private Collection<Ad> ads;
}