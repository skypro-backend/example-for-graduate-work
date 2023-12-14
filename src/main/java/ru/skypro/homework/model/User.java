package ru.skypro.homework.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Role;
import javax.persistence.*;

/**
 * The User class represents a user entity in the database. It stores information about a user,
 * including their unique identifier (id), username, password, first name, last name, phone number, email associated avatar and role.
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "email")
    private String email;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @OneToMany
    @JoinColumn(name = "role_id")
    @Enumerated(EnumType.STRING)
    private Role role;


}
