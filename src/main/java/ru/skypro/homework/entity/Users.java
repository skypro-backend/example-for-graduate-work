package ru.skypro.homework.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public enum Roles {
        USER, ADMIN
    }

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Roles roles;

    @OneToMany
    private List<Ads> adsList;

    @OneToMany
    private List<AdsComment> adsCommentList;
}
