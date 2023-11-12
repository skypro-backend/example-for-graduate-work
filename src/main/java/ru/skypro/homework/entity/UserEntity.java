package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    private int id;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;
    @OneToOne
    @JoinColumn(name = "USER_IMAGE", nullable = true)
    private Image imageAvatar;
    private String username;
    private String password;

    @OneToMany(mappedBy = "userRelated",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    List<Ad> ads = new ArrayList<>();

    @OneToMany(mappedBy = "userRelated",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    List<Ad> comments = new ArrayList<>();

}
