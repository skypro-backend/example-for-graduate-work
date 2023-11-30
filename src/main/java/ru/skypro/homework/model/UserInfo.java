package ru.skypro.homework.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.skypro.homework.dto.Role;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс описывающий сущность Пользователь
 */
@Entity
@Data
public class UserInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Ads> ads;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "image_id")
    private Image image;

}