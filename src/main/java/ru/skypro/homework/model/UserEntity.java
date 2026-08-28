package ru.skypro.homework.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность для таблицы "users".
 * Хранит данные пользователя в базе данных.
 * Основные функции:
 * - хранение учётных данных пользователя (email, пароль, имя, фамилия, телефон, роль);
 * - связь с объявлениями пользователя (OneToMany);
 * - связь с комментариями пользователя (OneToMany);
 * - связь с аватаром пользователя (OneToOne).
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 32)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 16)
    private String firstName;

    @Column(length = 16)
    private String lastName;

    @Column(length = 20)
    private String phone;

    @Column(length = 10)
    private String role;


    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<AdEntity> ads;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<CommentEntity> comments;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id")
    private ImageEntity avatar;
}
