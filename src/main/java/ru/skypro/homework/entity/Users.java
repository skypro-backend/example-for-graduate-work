package ru.skypro.homework.entity;


import lombok.*;
import ru.skypro.homework.dto.Role;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;


import javax.persistence.*;

/**
 * The class-entity with user's data in database
 * Also this class uses in checking for authentication and authorization, implements UserDetails's class
 * @author Sulaeva Marina
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image image;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String phone;

    @Enumerated
    private Role role;

}