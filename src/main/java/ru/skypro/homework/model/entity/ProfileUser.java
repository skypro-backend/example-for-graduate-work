package ru.skypro.homework.model.entity;

import lombok.Data;
import ru.skypro.homework.model.dto.RoleEnum;

import javax.persistence.*;
import java.util.Set;

import javax.persistence.Table;
/**
 * User
 */

@Entity
@Data
@Table(name = "profile_user")
public class ProfileUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @OneToMany(mappedBy = "author")
    private Set<Ads> ads;

    @Column(name = "reg_date")
    private String regDate;
    @Column(name = "city")
    private String city;
    @Column(name = "pass")
    private String password;
    @Column(name = "role")
    private RoleEnum role;

    @OneToOne(cascade = CascadeType.ALL)
    private Avatar avatar;

}
