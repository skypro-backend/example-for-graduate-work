package ru.skypro.homework.entity;

import lombok.Data;
import ru.skypro.homework.dto.Role;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    @Enumerated(EnumType.ORDINAL)
    private Role role;
    private String username;
    private String password;
    @OneToMany
    List<Ads> adsList;
    @OneToMany
    List<AdsComment> adsCommentList;
}