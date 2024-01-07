package ru.skypro.homework.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * <h2>User entity</h2>
 * <br>
 * <b>Fields:</b><br>
 * private Long id;
 * private String name; 2-16 symbols<br>
 * private String surname; 2-16 symbols<br>
 * private String phoneNumber;<br>
 * private String email; <-- must be valid<br>
 * *     private Role userRole;<br>
 * private String idImage;<br>
 * private String password; 8-16 symbols <br>
 */

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @Size(min = 2, max = 10)
    private String name;

    @Column(name = "surname")
    @Size(min = 2, max = 16)
    private String surname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    @Email(message = "Email must to be valid")
    private String email;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role userRole;

    @Column(name = "avatar")
    private String idImage;

    @Column(name = "password")
    @Size(min = 8, max = 16)
    private String password;


    public User(Integer id, String name,
                String surname, String phoneNumber,
                String email, Role userRole,
                String idImage, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userRole = userRole;
        this.idImage = idImage;
        this.password = password;

    }
}
