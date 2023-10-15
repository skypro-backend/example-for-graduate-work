package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Сущность пользователя
 */
@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
    /**
     * Id пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    /**
     * mail пользователя
     */
    @Column(name="email",  nullable = false, unique = true)
    private String email;
    /**
     * имя пользователя
     */
    @Column(name="first_name")
    private String firstName;
    /**
     * фамилия пользователя
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * телефон пользователя
     */
    @Column(name="phone", length = 20)
    private String phone;
    /**
     * права доступа пользователя
     */
    @Column(name="user_role", nullable = false)
    //@Enumerated(EnumType.STRING)
    private Role role;
    /**
     * aватар(фото) пользователя
     */

    @Column(name="user_image")
    private String image;
    /**
     * Объявления пользователя
     */
    //@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    //private List<Ad> ads;

     @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)

     private Collection<Ad> ads;

 }
