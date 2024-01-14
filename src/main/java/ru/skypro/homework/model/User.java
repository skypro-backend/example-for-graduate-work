package ru.skypro.homework.model;

import lombok.*;
import ru.skypro.homework.dto.Role;
import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String firstName;
    private String lastName;

    private String password;

    private String phone;

    private Role role;
    @OneToOne
    private Avatar avatar;

}
