package ru.skypro.homework.entity;

import lombok.*;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.ads.Ad;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode
@ToString
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    List<AdEntity> ad = new ArrayList<>();

//    @OneToMany(mappedBy = "userRelated",
//            fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL)
//    List<Comment> comments = new ArrayList<>();

}
