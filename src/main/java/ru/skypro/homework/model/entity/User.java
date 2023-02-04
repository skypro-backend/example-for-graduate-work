package ru.skypro.homework.model.entity;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Table;

/**
 * User
 */

@Entity
@Data
@Table(name = "user")
public class User {
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
    private LocalDate regDate;

    @Column(name = "city")
    private String city;

    @Lob
    @Column(name = "avatar")
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] avatar;

}
